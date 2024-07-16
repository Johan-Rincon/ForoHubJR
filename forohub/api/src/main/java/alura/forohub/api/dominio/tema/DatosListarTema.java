package alura.forohub.api.dominio.tema;

public record DatosListarTema(
        Long id,
        String titulo,
        String mensaje,
        String autor) {

    public DatosListarTema(Tema tema){
        this(tema.getId(), tema.getTitulo(), tema.getMensaje(), tema.getAutor().getNombre());
    }
}
