package pe.marcolopez.sistemas.vemoapp.entity.venta;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "Articulo")
@Table(name = "ARTICULO")
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqArticulo")
    @SequenceGenerator(name = "seqArticulo", allocationSize = 1, sequenceName = "SEQ_ARTICULO")
    @Builder.Default
    private Long id = 0L;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne(targetEntity = UnidadMedidaEntity.class)
    private UnidadMedidaEntity unidadMedida;
}
