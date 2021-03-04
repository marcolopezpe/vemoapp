package pe.marcolopez.sistemas.vemoapp.dto.generic;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class GenericDTO implements Serializable {

    private Long id = 0L;

    @Range(min = 0, max = 1, message = "El Estado es obligatorio y debe ser 1 o 0.")
    private Integer estado = 0;
}
