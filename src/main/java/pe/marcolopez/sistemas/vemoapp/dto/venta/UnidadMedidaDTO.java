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
        "nombre",
        "estado"})
public class UnidadMedidaDTO extends GenericDTO {

    @Size(min = 1, max = 20, message = "El Nombre es obligatorio y debe ser mayor que 5 y m\u00E1ximo 20.")
    private String nombre;
}
