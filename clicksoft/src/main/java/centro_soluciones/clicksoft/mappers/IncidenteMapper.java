package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.IncidenteDTO;
import centro_soluciones.clicksoft.entity.IncidenteEntity;

// Interface para declarar los Mapeos de las entidades y DTO's
public interface IncidenteMapper {
    // Mapeos de entity a DTO y de DTO a entity

    // Convertir a DTO
    IncidenteDTO toDto(IncidenteEntity incidenteEntity);

    // Convertir a Entity
    IncidenteEntity toEntity(IncidenteDTO incidenteDTO);

}
