package pe.marcolopez.sistemas.vemoapp.entity.security;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "Rol")
@Table(name = "ROL")
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRol")
    @SequenceGenerator(name = "seqRol", allocationSize = 1, sequenceName = "SEQ_ROL")
    @Builder.Default
    private Long id = 0L;

    @Column(name = "NOMBRE")
    private String nombre;
}
