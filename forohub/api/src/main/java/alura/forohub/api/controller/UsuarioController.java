package alura.forohub.api.controller;

import alura.forohub.api.dominio.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistro,
                                                            UriComponentsBuilder uriComponentsBuilder) {

        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistro));

        DatosRespuestaUsuario respuesta = new DatosRespuestaUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarUsuario>> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion) {

        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListarUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> regresarUsuario(@PathVariable Long id){

        Usuario usuario = usuarioRepository.getReferenceById(id);

        var datosUsuario = new DatosRespuestaUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){

        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());

        usuario.actualizarUsuario(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
