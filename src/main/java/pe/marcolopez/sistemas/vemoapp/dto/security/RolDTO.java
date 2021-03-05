package pe.marcolopez.sistemas.vemoapp.dto.security;

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
public class RolDTO extends GenericDTO {

    @Size(min = 3, max = 50, message = "El Nombre es obligatorio y debe ser mayor que 3 y máximo 50 caracteres")
    private String nombre;
}
