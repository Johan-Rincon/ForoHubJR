package alura.forohub.api.dominio.tema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTema(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long idAutor) {
}
