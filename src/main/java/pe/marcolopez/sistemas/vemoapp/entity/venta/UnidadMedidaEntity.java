package pe.marcolopez.sistemas.vemoapp.entity.venta;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "UnidadMedida")
@Table(name = "UNIDAD_MEDIDA")
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedidaEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUnidadMedida")
    @SequenceGenerator(name = "seqUnidadMedida", allocationSize = 1, sequenceName = "SEQ_UNIDAD_MEDIDA")
    @Builder.Default
    private Long id = 0L;

    @Column(name = "NOMBRE")
    private String nombre;
}
