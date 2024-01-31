package centro_soluciones.clicksoft.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
//Hacer referencia que es una entidad
@Entity
//Nombre de la tabla en la base de datos
@Table(name="USUARIOS")
//Colocar Data para generar los get y set del entity
@Data
public class UsuarioEntity {


    @Id
    //    Se coloca el nombre de la secuencia de la base de datos con el sequenceName y el name es solo el nombre que se usara en el generator
    @SequenceGenerator(name = "usuarioIdSequence", sequenceName = "USUARIOS_ID_USUARIO_SEQ", allocationSize = 1)
   //   Se coloca un GeneratedValue para decirle que tendra in ID generado por una secuencia y le pasamos su nombre con el generator
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioIdSequence")
    @Column(name = "ID_USUARIO" , nullable = false)
    Integer id;

    @Column(name = "NOMBRE_USUARIO" , length = 50, nullable = false)
    String nombreUsuario;

    @Column(name = "APELLIDO_PATERNO" , length = 50, nullable = false)
    String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO" , length = 50, nullable = false)
    String apellidoMaterno;

    @Column(name = "CORREO_USUARIO" , length = 50, nullable = false)
    String ccorreoUsuario;

    @Column(name = "PASSWORD_USUARIO" , length = 50, nullable = false)
    String passwordUsuario;

    @Column(name = "ROL_USUARIO" , length = 50, nullable = false)
    String rolUsuario;
}
