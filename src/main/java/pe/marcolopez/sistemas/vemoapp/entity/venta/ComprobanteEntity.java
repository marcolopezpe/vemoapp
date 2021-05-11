package pe.marcolopez.sistemas.vemoapp.entity.venta;

import lombok.*;
import org.hibernate.annotations.Where;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity(name = "Comprobante")
@Table(name = "COMPROBANTE")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ComprobanteEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqComprobante")
    @SequenceGenerator(name = "seqComprobante", allocationSize = 1, sequenceName = "SEQ_COMPROBANTE")
    @Builder.Default
    @EqualsAndHashCode.Include
    private Long id = 0L;

    @Column(name = "SERIE")
    private String serie;

    @Column(name = "NUMERO")
    private String numero;

    @ManyToOne(targetEntity = ClienteEntity.class)
    private ClienteEntity cliente;

    @Column(name = "FECHA")
    private Date fecha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comprobante")
    @Where(clause = "estado=1")
    private List<ComprobanteDetalleEntity> detalles;
}
