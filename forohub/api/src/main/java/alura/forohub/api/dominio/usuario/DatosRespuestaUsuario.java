package alura.forohub.api.dominio.usuario;

public record DatosRespuestaUsuario(
        String nombre,
        String email,
        String contrasena) {

    public DatosRespuestaUsuario (Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
    }
}
