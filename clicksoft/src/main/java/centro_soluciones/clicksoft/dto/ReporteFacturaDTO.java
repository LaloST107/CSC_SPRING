package centro_soluciones.clicksoft.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReporteFacturaDTO {

    private Integer id;

    private String nombreReporte;

    private String descripcion;

    private String fecha;

    private String status;

    private String plaza;

    private String tienda;

    private Integer registroActivo;

    private Integer idTicket;
}//end class
