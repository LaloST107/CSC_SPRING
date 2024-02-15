package centro_soluciones.clicksoft.entity;


//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//Hacer referencia que es una entidad
@Entity
//Nombre de la tabla en la base de datos
@Table(name="USUARIOS")
//Colocar Data para generar los get y set del entity
@Data
public class User {


    @Id
    //    Se coloca el nombre de la secuencia de la base de datos con el sequenceName y el name es solo el nombre que se usara en el generator
    @SequenceGenerator(name = "usuarioIdSequence", sequenceName = "USUARIOS_ID_USUARIO_SEQ", allocationSize = 1)
   //   Se coloca un GeneratedValue para decirle que tendra in ID generado por una secuencia y le pasamos su nombre con el generator
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioIdSequence")
    @Column(name = "ID_USUARIO" , nullable = false)
    private Long id;

    @Column(name = "NOMBRE_USUARIO" , length = 50, nullable = false)
    private String nombreUsuario;

    @Column(name = "APELLIDO_PATERNO" , length = 50, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO" , length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "CORREO_USUARIO" , length = 50, nullable = false)
    private String correoUsuario;

    @Column(name = "PASSWORD_USUARIO" , length = 50, nullable = false)
    private String passwordUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_USUARIO" , nullable = false)
//    private String rolUsuario;
    private Role rolUsuario;

    @Transient
    private String token;

}
