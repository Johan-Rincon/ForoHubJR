package alura.forohub.api.dominio.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long idTema,
        @NotBlank
        Long idAutor,
        @NotNull
        Boolean solucion) {
}
