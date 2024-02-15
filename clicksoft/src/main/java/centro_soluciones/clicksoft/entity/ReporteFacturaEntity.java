package centro_soluciones.clicksoft.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "ReporteFacturaT")
@Table(name = "REPORTE_TICKET")
public class ReporteFacturaEntity {
    @Id
    @SequenceGenerator(name = "reporteFacturaIdSequence", sequenceName = "REPORTE_TICKET_ID_REPORTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reporteFacturaIdSequence")

    @Column(name = "ID_REPORTE")
    private Integer id;

    @Column(name = "NOMBRE_REPORTE", nullable = false)
    private String nombreReporte;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "PLAZA", nullable = false)
    private String plaza;

    @Column(name = "TIENDA", nullable = false)
    private String tienda;

    @Column(name="REGISTRO_ACTIVO", nullable=false)
    private Integer registroActivo;

    @Column(name ="ID_TICKET", nullable = false)
    private Integer idTicket;

}//end class
