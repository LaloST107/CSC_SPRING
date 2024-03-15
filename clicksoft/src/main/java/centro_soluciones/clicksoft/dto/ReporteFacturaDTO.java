package centro_soluciones.clicksoft.dto;


import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;
import centro_soluciones.clicksoft.entity.ReporteTicketPlazaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

    //private List<ReporteTicketPlazaDTO> reporteTicketPlazaDTOS;

    //private ReporteTicketPlazaEntity ticket;

}//end class
