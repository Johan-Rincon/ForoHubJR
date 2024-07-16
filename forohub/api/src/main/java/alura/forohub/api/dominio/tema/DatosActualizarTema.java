package alura.forohub.api.dominio.tema;

import alura.forohub.api.dominio.estado.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTema(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Estado estado,
        @NotNull
        Long idAutor) {
}
