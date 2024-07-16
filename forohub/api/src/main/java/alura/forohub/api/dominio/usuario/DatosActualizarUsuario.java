package alura.forohub.api.dominio.usuario;

public record DatosActualizarUsuario(
        Long id,
        String nombre,
        String email,
        String contrasena) {
}
