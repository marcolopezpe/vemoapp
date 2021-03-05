package pe.marcolopez.sistemas.vemoapp.dto.venta;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.dto.generic.GenericDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "fecha",
        "serie",
        "numero",
        "cliente",
        "detalles",
        "estado"})
public class ComprobanteDTO extends GenericDTO {

    @Size(min = 3, max = 10, message = "La Serie es obligatoria y debe ser mayor que 5 y m\u00E1ximo 10.")
    private String serie;

    @Size(min = 5, max = 20, message = "El Numero es obligatorio y debe ser mayor que 5 y m\u00E1ximo 20.")
    private String numero;

    private ClienteDTO cliente;

    @NotNull(message = "La Fecha es obligatoria")
    private Date fecha;

    private List<ComprobanteDetalleDTO> detalles;
}
