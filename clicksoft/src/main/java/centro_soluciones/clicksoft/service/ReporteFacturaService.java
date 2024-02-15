package centro_soluciones.clicksoft.service;

import centro_soluciones.clicksoft.dto.ReporteFacturaDTO;
import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import centro_soluciones.clicksoft.service.generic.GenericService;

import java.util.List;

public interface ReporteFacturaService extends GenericService<ReporteFacturaDTO>{
    List<ReporteFacturaDTO> getNombreReporte(String keyword);
}//end class
