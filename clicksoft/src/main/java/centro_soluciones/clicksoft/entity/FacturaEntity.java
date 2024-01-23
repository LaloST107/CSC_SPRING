package centro_soluciones.clicksoft.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
//Colocar Data para generar los get y set del entity
@Data
//Hacer referencia que es una entidad
@Entity(name ="FacturaC" )
//Nombre de la tabla en la base de datos
@Table(name = "FACTURA")
public class FacturaEntity {

    @Id
//    Se coloca el nombre de la secuencia de la base de datos con el sequenceName y el name es solo el nombre que se usara en el generator
    @SequenceGenerator(name = "facturaIdSequence", sequenceName = "FACTURA_ID_FOLIO_SEQ", allocationSize = 1)
//   Se coloca un GeneratedValue para decirle que tendra in ID generado por una secuencia y le pasamos su nombre con el generator
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facturaIdSequence")
//    Hacemos el mapeo de las columnas de la base de datos con la variable , a cdaa una de ellas se le coloca que no debe ser null esto sera importante al momento de insertar
    @Column(name="ID_FOLIO")
    private Integer id;

    @Column(name = "FOLIO", nullable = false)
    private Integer folio;

    @Column(name = "STATUS",  length = 50,  nullable = false)
    private String status;

    @Column(name = "CASO_REPORTADO" ,  length = 50,  nullable = false)
    private String casoReportado;

    @Column(name = "CATEGORIA_SOLUCION",  length = 50,  nullable = false)
    private String categoriaSolucion;

    @Column(name = "CATEGORIA_PROBLEMA",  length = 50,  nullable = false)
    private String categoriaProblema;

    @Column(name = "CORREO_CLIENTE",  length = 50,  nullable = false)
    private String correoCliente;

    @Column(name = "NUMERO_FACTURA", nullable = false)
    private Integer numeroFactura;

    @Column(name = "FOLIO_VENTA", length = 50, nullable = false)
    private String folioVenta;

    @Column(name = "CATEGORIA_FACTURACION", length = 50, nullable = false)
    private String categoriaFacturacion;

    @Column(name = "FECHA_RECEPCION", nullable = false)
    private Date fechaRecepcion;

    @Column(name = "FECHA_PRIMER_ATENCION", nullable = false)
    private Date fechaPrimeraAtencion;
//Colocamos las fechas de cierre y se solucion sin ningun nullable ya que al guardarlas como null hace conflictos
    @Column(name = "FECHA_SOLUCION")
    private Date fechaSolucion;

    @Column(name = "FECHA_CIERRE")
    private Date fechaCierre;

    @Column(name = "REGISTRO_ACTIVO",  nullable = false)
    private Integer registroActivo;



}

