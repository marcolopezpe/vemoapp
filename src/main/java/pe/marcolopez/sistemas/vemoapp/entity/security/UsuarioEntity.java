package pe.marcolopez.sistemas.vemoapp.entity.security;

import lombok.*;
import pe.marcolopez.sistemas.vemoapp.entity.generic.GenericEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity(name = "Usuario")
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity extends GenericEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
    @SequenceGenerator(name = "seqUsuario", allocationSize = 1, sequenceName = "SEQ_USUARIO")
    @Builder.Default
    private Long id = 0L;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "CONTRASENA")
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private List<RolEntity> roles;
}
