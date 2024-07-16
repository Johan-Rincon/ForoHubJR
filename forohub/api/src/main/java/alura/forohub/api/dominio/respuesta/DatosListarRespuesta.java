package alura.forohub.api.dominio.respuesta;

public record DatosListarRespuesta(
        Long id,
        String mensaje,
        String tema,
        String autor) {

    public DatosListarRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTema().getTitulo(), respuesta.getAutor().getNombre());
    }
}
