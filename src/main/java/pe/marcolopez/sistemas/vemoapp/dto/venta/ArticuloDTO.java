package pe.marcolopez.sistemas.vemoapp.dto.venta;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.dto.generic.GenericDTO;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "codigo",
        "descripcion",
        "unidadMedida",
        "estado"})
public class ArticuloDTO extends GenericDTO {

    @Size(min = 5, max = 20, message = "El C\u00F3digo es obligatorio y debe ser mayor que 5 y m\u00E1ximo 20.")
    private String codigo;

    @Size(min = 5, max = 200, message = "La Descripci\u00F3n es obligatoria y debe ser mayor que 5 y m\u00E1ximo 20.")
    private String descripcion;

    private UnidadMedidaDTO unidadMedida;
}
