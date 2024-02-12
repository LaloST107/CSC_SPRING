package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.ReporteTicketPlazaDTO;
import centro_soluciones.clicksoft.entity.ReporteTicketPlazaEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ReporteTicketMImpl implements ReporteTicketMapper{

    //formato para fecha
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    //función donde se transforma DTO a entidad
    @Override
    public ReporteTicketPlazaDTO toDto(ReporteTicketPlazaEntity e) {
        return ReporteTicketPlazaDTO.builder()
                .idTicket(e.getIdTicket())
                .folioVenta(e.getFolioVenta())
                .plaza(e.getPlaza())
                .tienda(e.getTienda())
                .status(e.getStatus())
                .entidad(e.getEntidad())
                .fecha(String.valueOf(e.getFecha()).formatted(formato))
                .subtotalVenta(e.getSubtotalVenta())
                .totalVenta(e.getTotalVenta())
                .caja(e.getCaja())
                .numTicket(e.getNumTicket())
                .numRegistroAct(e.getNumRegistroAct())
                .usuario(e.getUsuario())
                .build();
    }

    //función donde se transforma Entidad a DTO
    @Override
    public ReporteTicketPlazaEntity toEntity(ReporteTicketPlazaDTO reporteDTO) {

        Date formatoDate = new Date();

        ReporteTicketPlazaEntity ticketEntity = new ReporteTicketPlazaEntity();

        ticketEntity.setFolioVenta(reporteDTO.getFolioVenta());
        ticketEntity.setPlaza(reporteDTO.getPlaza());
        ticketEntity.setTienda(reporteDTO.getTienda());
        ticketEntity.setStatus(reporteDTO.getStatus());
        ticketEntity.setEntidad(reporteDTO.getEntidad());
        ticketEntity.setFecha(formatoDate);
        ticketEntity.setSubtotalVenta(reporteDTO.getSubtotalVenta());
        ticketEntity.setTotalVenta(reporteDTO.getTotalVenta());
        ticketEntity.setCaja(reporteDTO.getCaja());
        ticketEntity.setNumTicket(reporteDTO.getNumTicket());
        ticketEntity.setNumRegistroAct(1);
        ticketEntity.setUsuario(reporteDTO.getUsuario());

        return ticketEntity;
    }
}
