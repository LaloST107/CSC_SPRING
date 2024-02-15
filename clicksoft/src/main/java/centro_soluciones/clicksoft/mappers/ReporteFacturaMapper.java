package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.ReporteFacturaDTO;
import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;

public interface ReporteFacturaMapper {

    ReporteFacturaDTO toDto(ReporteFacturaEntity e);

    ReporteFacturaEntity toEntity(ReporteFacturaDTO reporteFacturaDTO);

    ReporteFacturaDTO toDtoId(ReporteFacturaEntity e);
}
