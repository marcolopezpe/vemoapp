package pe.marcolopez.sistemas.vemoapp.entity.venta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity(name = "ComprobanteDetalle")
@Table(name = "COMPROBANTE_DETALLE")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ComprobanteDetalleEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqComprobanteDetalle")
    @SequenceGenerator(name = "seqComprobanteDetalle", allocationSize = 1, sequenceName = "SEQ_COMPROBANTE_DETALLE")
    @Builder.Default
    @EqualsAndHashCode.Include
    private Long id = 0L;

    @ManyToOne(targetEntity = ArticuloEntity.class)
    private ArticuloEntity articulo;

    @Column(name = "PRECIO_UNITARIO")
    private BigDecimal precioUnitario;

    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;

    @Column(name = "KILOS")
    private BigDecimal kilos;

    @JoinColumn(name = "COMPROBANTE_ID")
    @ManyToOne(targetEntity = ComprobanteEntity.class)
    @JsonIgnore
    private ComprobanteEntity comprobante;
}
