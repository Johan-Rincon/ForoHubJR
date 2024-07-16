package alura.forohub.api.dominio.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    //private Perfil perfil;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.contrasena = datosRegistroUsuario.contrasena();
    }

    public void actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {
        if(datosActualizarUsuario.nombre() != null){
            this.nombre = datosActualizarUsuario.nombre();
        }
        if(datosActualizarUsuario.email() != null){
            this.email = datosActualizarUsuario.email();
        }
        if(datosActualizarUsuario.contrasena() != null){
            this.contrasena = datosActualizarUsuario.contrasena();
        }
    }
}
