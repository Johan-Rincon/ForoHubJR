package alura.forohub.api.dominio.respuesta;

import alura.forohub.api.dominio.tema.Tema;
import alura.forohub.api.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario autor;
    private Boolean solucion;

    //Coming soon
}
