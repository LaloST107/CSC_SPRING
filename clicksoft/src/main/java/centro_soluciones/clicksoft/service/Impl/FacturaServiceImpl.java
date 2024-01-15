package centro_soluciones.clicksoft.service.Impl;


import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.repository.FacturaRepository;
import centro_soluciones.clicksoft.service.FacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(final FacturaRepository facturaRepository) {
        this.facturaRepository =  facturaRepository;
    }

    @Override
    public List<FacturaDTO> findAll() throws ServiceException {
        return null;
    }

    @Override
    public List<FacturaDTO> findByLikeObject(FacturaDTO facturaDTO) throws ServiceException {
        return null;
    }

    @Override
    public FacturaDTO save(FacturaDTO facturaDTO) throws ServiceException {
        return null;
    }

    @Override
    public FacturaDTO update(FacturaDTO facturaDTO) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Integer id) throws ServiceException {

    }

    @Override
    public Optional<FacturaDTO> findById(Integer id) throws ServiceException {
        return Optional.empty();
    }
}
