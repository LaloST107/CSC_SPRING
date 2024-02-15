package centro_soluciones.clicksoft.dto;

import centro_soluciones.clicksoft.entity.User;
//import centro_soluciones.clicksoft.entity.UsuarioEntity;
//import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTicketPlazaDTO {

    private Integer idTicket;

    private Integer folioVenta;

    private String plaza;

    private String tienda;

    private String status;

    private String entidad;

    private String fecha;

    private Integer subtotalVenta;

    private Integer totalVenta;

    private Integer caja;

    private Integer numTicket;

    private Integer numRegistroAct;

    private User usuario;
//    private UsuarioEntity usuario;

}
