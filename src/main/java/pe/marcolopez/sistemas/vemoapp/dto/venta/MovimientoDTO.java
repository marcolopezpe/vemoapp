package pe.marcolopez.sistemas.vemoapp.dto.venta;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.dto.generic.GenericDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "fecha",
        "articulo",
        "tipo",
        "cantidad",
        "kilos",
        "estado"})
public class MovimientoDTO extends GenericDTO {

    private ArticuloDTO articulo;

    @Size(min = 1, max = 1, message = "El Tipo es obligatorio y debe ser 1 caracter.")
    private String tipo;

    @Min(value = 1, message = "La Cantidad es obligatoria y debe ser minimo 1.")
    private BigDecimal cantidad;

    @NotNull(message = "Los Kilos son obligatorios")
    private BigDecimal kilos;

    @NotNull(message = "La Fecha es obligatorio")
    private Date fecha;

    private String comprobanteNumero;
}
