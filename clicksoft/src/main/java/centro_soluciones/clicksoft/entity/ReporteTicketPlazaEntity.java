package centro_soluciones.clicksoft.entity;


//import jakarta.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity(name = "TicketEntity")
@Table(name = "TICKET_PLAZA")
public class ReporteTicketPlazaEntity {

    @Id
    @SequenceGenerator(name = "TicketIdSequence", sequenceName = "TICKET_PLAZA_ID_TICKET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TicketIdSequence")
    @Column(name = "ID_TICKET")
    private Integer idTicket;

    @Column(name = "FOLIO_VENTA", nullable = false)
    private Integer folioVenta;

    @Column(name = "PLAZA", length = 50,nullable = false)
    private String plaza;

    @Column(name = "TIENDA", length = 35,nullable = false)
    private String tienda;

    @Column(name = "STATUS", length = 35,nullable = false)
    private String status;

    @Column(name = "ENTIDAD", length = 35,nullable = false)
    private String entidad;

    @Column(name = "FECHA",  nullable = false)
    private Date fecha;

    @Column(name = "SUBTOTAL_VENTA", nullable = false)
    private Integer subtotalVenta;

    @Column(name = "TOTAL_VENTA", nullable = false)
    private Integer totalVenta;

    @Column(name = "CAJA", nullable = false)
    private Integer caja;

    @Column(name = "NUM_TICKET", nullable = false)
    private Integer numTicket;

    @Column(name = "REGISTRO_ACTIVO", nullable = false)
    private Integer numRegistroAct;

    //@ManyToOne
//    @Column(name = "ID_USUARIO", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO")
    private User usuario;
    //UsuarioEntity

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ID_TICKET", nullable = false)
    private ReporteFacturaEntity reporteFactura;

}
