package centro_soluciones.clicksoft.entity.security;

import jakarta.persistence.*;

public class UserEntity {
    @Id
    //Asignar algun name, el sequenceName es nombre de la secuencia de la BD
    @SequenceGenerator(name = "facturaIdSequence" , sequenceName = "FACTURA_ID_FOLIO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facturaIdSequence")
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;



}
