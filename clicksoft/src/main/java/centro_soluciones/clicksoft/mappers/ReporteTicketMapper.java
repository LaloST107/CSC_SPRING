package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.ReporteTicketPlazaDTO;
import centro_soluciones.clicksoft.entity.ReporteTicketPlazaEntity;

public interface ReporteTicketMapper {

    //Método para convertir Entidad a DTO
    ReporteTicketPlazaDTO toDto(ReporteTicketPlazaEntity e);

    //Método para convertir DTO a Entidad
    ReporteTicketPlazaEntity toEntity(ReporteTicketPlazaDTO reporteDTO);
}
