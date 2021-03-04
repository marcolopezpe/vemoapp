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
        "razonSocial",
        "tipoDocumento",
        "numeroDocumento",
        "direccion",
        "nombreContacto",
        "emailContacto",
        "estado"})
public class ClienteDTO extends GenericDTO {

    @Size(min = 10, max = 200, message = "La Raz\u00F3n Social es obligatoria y debe ser mayor que 5 y m\u00E1ximo 200.")
    private String razonSocial;

    @Size(min = 2, max = 15, message = "El Tipo de Documento es obligatorio y debe ser mayor que 5 y m\u00E1ximo 15.")
    private String tipoDocumento;

    @Size(min = 5, max = 20, message = "El N\u00FAmero de Documento es obligatorio y debe ser mayor que 5 y m\u00E1ximo 20.")
    private String numeroDocumento;

    @Size(min = 10, max = 500, message = "La Direcci\u00F3n es obligatoria y debe ser mayor que 10 y m\u00E1ximo 500.")
    private String direccion;

    private String nombreContacto;

    private String emailContacto;
}
