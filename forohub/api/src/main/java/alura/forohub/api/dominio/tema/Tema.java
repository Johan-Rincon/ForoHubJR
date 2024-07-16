package alura.forohub.api.dominio.tema;

import alura.forohub.api.dominio.estado.Estado;
import alura.forohub.api.dominio.respuesta.Respuesta;
import alura.forohub.api.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "temas")
@Entity(name = "Tema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario autor;
    //private Curso curso;
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    public Tema(DatosRegistroTema datosRegistroTema, Usuario usuario) {
        this.titulo = datosRegistroTema.titulo();
        this.mensaje = datosRegistroTema.mensaje();
        this.autor = usuario;
    }

    public void actualizarTema(DatosActualizarTema datosActualizarTema) {
        if (datosActualizarTema.titulo() != null) {
            this.titulo = datosActualizarTema.titulo();
        }
        if (datosActualizarTema.mensaje() != null) {
            this.mensaje = datosActualizarTema.mensaje();
        }
        if (datosActualizarTema.estado() != null) {
            this.estado = datosActualizarTema.estado();
        }
    }
}
