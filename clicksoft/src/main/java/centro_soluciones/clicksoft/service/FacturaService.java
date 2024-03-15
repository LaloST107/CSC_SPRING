package centro_soluciones.clicksoft.service;


import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import centro_soluciones.clicksoft.service.generic.GenericService;

public interface FacturaService extends GenericService<FacturaDTO> {


// Metodo para actualizar la fecha de solución y actualizar el status a Solucionado
    FacturaDTO updateDateSolution(Integer id) throws ServiceException;

    FacturaDTO updateDateClose(Integer id) throws ServiceException;
}
