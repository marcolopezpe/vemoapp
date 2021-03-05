package pe.marcolopez.sistemas.vemoapp.dto.venta;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.dto.generic.GenericDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "articulo",
        "precioUnitario",
        "cantidad",
        "kilos",
        "estado"})
public class ComprobanteDetalleDTO extends GenericDTO {

    private ArticuloDTO articulo;

    @NotNull(message = "El Precio Unitario es obligatorio")
    private BigDecimal precioUnitario;

    @Min(value = 1, message = "La Cantidad es obligatoria y debe ser minimo 1.")
    private Integer cantidad;

    @NotNull(message = "Los Kilos son obligatorios")
    private BigDecimal kilos;
}
