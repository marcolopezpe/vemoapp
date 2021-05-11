package pe.marcolopez.sistemas.vemoapp.entity.venta;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "SerieComprobrante")
@Table(name = "SERIE_COMPROBANTE")
@NoArgsConstructor
@AllArgsConstructor
public class SerieComprobanteEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSerieComprobante")
    @SequenceGenerator(name = "seqSerieComprobante", allocationSize = 1, sequenceName = "SEQ_SERIE_COMPROBANTE")
    @Builder.Default
    private Long id = 0L;

    @Column(name = "NUMERO")
    private String numero;
}
