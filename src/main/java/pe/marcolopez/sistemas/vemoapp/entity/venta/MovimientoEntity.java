package pe.marcolopez.sistemas.vemoapp.entity.venta;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "Movimiento")
@Table(name = "MOVIMIENTO")
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMovimiento")
    @SequenceGenerator(name = "seqMovimiento", allocationSize = 1, sequenceName = "SEQ_MOVIMIENTO")
    @Builder.Default
    private Long id = 0L;

    @ManyToOne(targetEntity = ArticuloEntity.class)
    private ArticuloEntity articulo;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "KILOS")
    private BigDecimal kilos;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
}
