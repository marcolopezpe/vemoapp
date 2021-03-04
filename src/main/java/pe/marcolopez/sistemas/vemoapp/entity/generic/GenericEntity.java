package pe.marcolopez.sistemas.vemoapp.entity.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class GenericEntity implements Serializable {

    @Column(name = "ESTADO")
    private Integer estado;
}
