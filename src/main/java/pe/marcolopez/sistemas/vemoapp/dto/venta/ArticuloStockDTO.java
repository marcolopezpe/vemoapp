package pe.marcolopez.sistemas.vemoapp.dto.venta;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "articulo",
        "cantidad",
        "kilos"})
public class ArticuloStockDTO implements Serializable {

    private ArticuloDTO articulo;

    private Integer cantidad;

    private BigDecimal kilos;
}
