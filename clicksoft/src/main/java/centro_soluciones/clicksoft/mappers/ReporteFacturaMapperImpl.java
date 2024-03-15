package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.ReporteFacturaDTO;
import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;
import centro_soluciones.clicksoft.service.Impl.ReporteFacturaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ReporteFacturaMapperImpl implements ReporteFacturaMapper{

    private static final Logger logger = LoggerFactory.getLogger(ReporteFacturaServiceImpl.class);
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    @Override
    public ReporteFacturaDTO toDto(ReporteFacturaEntity e) {
        return ReporteFacturaDTO.builder()
                .id(e.getId())
                .nombreReporte(e.getNombreReporte())
                .descripcion(e.getDescripcion())
                .fecha(String.valueOf(e.getFecha()).formatted(formato))
                .status(e.getStatus())
                .plaza(e.getPlaza())
                .tienda(e.getTienda())
                .idTicket(e.getIdTicket())
                .build();

    }

    @Override
    public ReporteFacturaEntity toEntity(ReporteFacturaDTO reporteFacturaDTO) {

        Date formatoDate = new Date();

        ReporteFacturaEntity reporteFacturaEntity = new ReporteFacturaEntity();

        reporteFacturaEntity.setNombreReporte(reporteFacturaDTO.getNombreReporte());
        reporteFacturaEntity.setDescripcion(reporteFacturaDTO.getDescripcion());
        reporteFacturaEntity.setFecha(formatoDate);
        reporteFacturaEntity.setStatus(reporteFacturaDTO.getStatus());
        reporteFacturaEntity.setPlaza(reporteFacturaDTO.getPlaza());
        reporteFacturaEntity.setTienda(reporteFacturaDTO.getTienda());
        reporteFacturaEntity.setRegistroActivo(1);
        reporteFacturaEntity.setIdTicket(reporteFacturaDTO.getIdTicket());

        logger.info("Error al insertar :0 " + reporteFacturaEntity);

        return reporteFacturaEntity;
    }

    @Override
    public ReporteFacturaDTO toDtoId(ReporteFacturaEntity e) {
        return ReporteFacturaDTO.builder()
                .id(e.getId())
                .nombreReporte(e.getNombreReporte())
                .descripcion(e.getDescripcion())
                .fecha(String.valueOf(e.getFecha()).formatted(formato))
                .status(e.getStatus())
                .plaza(e.getPlaza())
                .tienda(e.getTienda())
                .idTicket(e.getIdTicket())
                .build();
    }

}