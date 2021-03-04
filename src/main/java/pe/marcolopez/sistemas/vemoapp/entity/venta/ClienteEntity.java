package pe.marcolopez.sistemas.vemoapp.entity.venta;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "Cliente")
@Table(name = "CLIENTE")
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
    @SequenceGenerator(name = "seqCliente", allocationSize = 1, sequenceName = "SEQ_CLIENTE")
    @Builder.Default
    private Long id = 0L;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "NOMBRE_CONTACTO")
    private String nombreContacto;

    @Column(name = "EMAIL_CONTACTO")
    private String emailContacto;
}
