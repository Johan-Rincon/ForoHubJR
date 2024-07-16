package alura.forohub.api.controller;

import alura.forohub.api.dominio.tema.*;
import alura.forohub.api.dominio.usuario.DatosRespuestaUsuario;
import alura.forohub.api.dominio.usuario.Usuario;
import alura.forohub.api.dominio.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/temas")
@SecurityRequirement(name = "bearer-key")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public TemaController (TemaRepository temaRepository, UsuarioRepository usuarioRepository) {
        this.temaRepository = temaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity <DatosRespuestaTema> registrarTema(@RequestBody @Valid DatosRegistroTema datosRegistro,
                                                             UriComponentsBuilder uriComponentsBuilder) {

        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.idAutor());
        Tema tema = temaRepository.save(new Tema(datosRegistro, autor));

        DatosRespuestaTema respuesta = new DatosRespuestaTema(tema.getId(), tema.getTitulo(), tema.getMensaje(),
                tema.getEstado().toString(), new DatosRespuestaUsuario(tema.getAutor()));

        URI url = uriComponentsBuilder.path("/temas/{id}").buildAndExpand(tema.getId()).toUri();

        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTema>> listadoTemas(@PageableDefault(size = 10) Pageable paginacion) {

        return ResponseEntity.ok(temaRepository.findAll(paginacion).map(DatosListarTema::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTema> regresarTema(@PathVariable Long id){

        Tema tema = temaRepository.getReferenceById(id);

        var datosTema = new DatosRespuestaTema(tema.getId(), tema.getTitulo(), tema.getMensaje(),
                tema.getEstado().toString(),  new DatosRespuestaUsuario(tema.getAutor()));
        return ResponseEntity.ok(datosTema);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTema> actualizarTema(@RequestBody @Valid DatosActualizarTema datosActualizarTema){

        //Usuario usuario = usuarioRepository.getReferenceById(datosActualizarTema.idAutor());
        Tema tema = temaRepository.getReferenceById(datosActualizarTema.id());

        tema.actualizarTema(datosActualizarTema);
        return ResponseEntity.ok(new DatosRespuestaTema(tema.getId(), tema.getTitulo(), tema.getMensaje(),
                tema.getEstado().toString(), new DatosRespuestaUsuario(tema.getAutor())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTema(@PathVariable Long id) {
        Tema tema = temaRepository.getReferenceById(id);
        temaRepository.delete(tema);
        return ResponseEntity.noContent().build();
    }
}
