package pe.marcolopez.sistemas.vemoapp.dto.venta;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.dto.generic.GenericDTO;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "numero",
        "estado"})
public class SerieComprobanteDTO extends GenericDTO {

    @NotNull(message = "El Numero es obligatorio")
    private String numero;
}
