package pe.marcolopez.sistemas.vemoapp.dto.security;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.dto.generic.GenericDTO;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "nombres",
        "apellidos",
        "usuario",
        "contrasena",
        "roles",
        "estado"})
public class UsuarioDTO extends GenericDTO {

    @Size(min = 3, max = 100, message = "Los Nombres son obligatorios y debe ser mayor que 3 y máximo 100 caracteres")
    private String nombres;

    @Size(min = 3, max = 100, message = "Los Apellidos son obligatorios y debe ser mayor que 3 y máximo 100 caracteres")
    private String apellidos;

    @Size(min = 3, max = 50, message = "El Usuario es obligatorio y debe ser mayor que 3 y máximo 50 caracteres")
    private String usuario;

    @Size(min = 3, max = 60, message = "La Contrasena es obligatoria y debe ser mayor que 3 y máximo 60 caracteres")
    private String contrasena;

    private List<RolDTO> roles;
}
