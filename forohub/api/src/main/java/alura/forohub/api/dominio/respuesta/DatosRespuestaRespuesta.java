package alura.forohub.api.dominio.respuesta;

import alura.forohub.api.dominio.tema.DatosRespuestaTema;
import alura.forohub.api.dominio.usuario.DatosRespuestaUsuario;

public record DatosRespuestaRespuesta(
        String mensaje,
        String tema,
        String autor) {

    public DatosRespuestaRespuesta(Respuesta respuesta){
        this(respuesta.getMensaje(), respuesta.getTema().getTitulo(), respuesta.getAutor(). getNombre());
    }
}
