package centro_soluciones.clicksoft.entity.security;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "SEG_USER")
public class UserEntity {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    @SequenceGenerator(name = "facturaIdSequence" , sequenceName = "FACTURA_ID_FOLIO_SEQ", allocationSize = 1)
    private Long id;

    @Column(name  = "USER_NAME", nullable = false )
    private String userName;

    @Column(name  = "PASSWORD", nullable = false )
    private String password;

    @Column(name  = "NAME", nullable = false )
    private String name;

    @ManyToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "SEG_USER_AUTHORITY" , joinColumns = { @JoinColumn(name = "USER_ID")} , inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")})
    private Set<AuthorityEntity> authorities = new HashSet<>();

}
