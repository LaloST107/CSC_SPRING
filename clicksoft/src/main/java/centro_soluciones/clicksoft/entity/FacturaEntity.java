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
@Entity
//Nombre de la tabla en la base de datos
@Table(name = "FACTURA")
public class FacturaEntity {

    @Id
    //Asignar algun name, el sequenceName es nombre de la secuencia de la BD
    @SequenceGenerator(name = "facturaIdSequence" , sequenceName = "FACTURA_ID_FOLIO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facturaIdSequence")
    //Relacionar nombre de la columna de la tabla y con variable creada
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

    @Column(name = "FECHA_RECEPCION",  nullable = false)
    private Date fechaRecepcion;

    @Column(name = "FECHA_PRIMERA_ATENCION",  nullable = false)
    private Date fechaPrimeraAtencion;

    @Column(name = "FECHA_SOLUCION",  nullable = false)
    private Date fechaSolucion;

    @Column(name = "FECHA_CIERRE",  nullable = false)
    private Date fechaCierre;

    @Column(name = "REGISTRO_ACTIVO",  nullable = false)
    private Integer registroActivo;



}

