package centro_soluciones.clicksoft.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name ="IncidenteC" )
@Table(name = "INCIDENTE")
public class IncidenteEntity {

    @Id
    @SequenceGenerator(name = "incidenteIdSequence" , sequenceName = "INCIDENTE_ID_INCIDENTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incidenteIdSequence")
    @Column(name = "ID_INCIDENTE")
    private Integer id;

    @Column(name = "NOMBRE_INCIDENTE", length = 35, nullable = false)
    private String nombreIncidente;

    @Column(name = "DESCRIPCION_INCIDENTE", length = 60, nullable = false)
    private String descripcionIncidente;

    @Column(name = "FECHA_INCIDENTE", nullable = false)
    private Date fechaIncidente;

    @Column(name = "DIRECCION_IMAGEN_INCIDENTE", length = 100, nullable = false)
    private String direccionImagenIncidente;

    @Column(name = "STATUS", length = 35, nullable = false)
    private String status;

    @Column(name = "IMPACTO", length = 35, nullable = false)
    private String impacto;

    @Column(name = "URGENCIA", length = 35, nullable = false)
    private String urgencia;

    @Column(name = "PRIORIDAD", length = 35, nullable = false)
    private String prioridad;

    @Column(name = "PRIORIDAD_EXTRA", length = 35)
    private String prioridadExtra;

    @Column(name = "REGISTRO_ACTIVO", nullable = false)
    private Integer registroActivo;
    /*
    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;
    */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private UsuarioEntity usuario;
}
