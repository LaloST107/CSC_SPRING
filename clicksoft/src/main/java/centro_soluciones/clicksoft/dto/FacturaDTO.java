package centro_soluciones.clicksoft.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//@JsonInclude(value = Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FacturaDTO {

    private Integer id;

    private Integer folio;

    private String status;

    private String casoReportado;

    private String categoriaSolucion;

    private String categoriaProblema;

    private String correoCliente;

    private Integer numeroFactura;

    private String folioVenta;

    private String categoriaFacturacion;

    private Date fechaRecepcion;

    private Date fechaPrimeraAtencion;

    private Date fechaSolucion;

    private Date fechaCierre;

    private Integer registroActivo;

}
