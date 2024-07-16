package alura.forohub.api.dominio.tema;

import alura.forohub.api.dominio.usuario.DatosRespuestaUsuario;

public record DatosRespuestaTema(Long id,
                                 String titulo,
                                 String mensaje,
                                 String status,
                                 DatosRespuestaUsuario autor) {

    public DatosRespuestaTema (Tema tema) {
        this(tema.getId(), tema.getTitulo(), tema.getMensaje(), tema.getEstado().toString(),
                new DatosRespuestaUsuario(tema.getAutor()));
    }
}
