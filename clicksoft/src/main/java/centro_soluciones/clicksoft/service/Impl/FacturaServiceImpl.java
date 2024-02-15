package centro_soluciones.clicksoft.service.Impl;


import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.entity.FacturaEntity;
import centro_soluciones.clicksoft.mappers.FacturaMapper;
import centro_soluciones.clicksoft.repository.FacturaRepository;
import centro_soluciones.clicksoft.service.FacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {
//  Hacemos la inyección por constructor enel cual le mandamos el repository y el factura mapper que tiene nuestros mapeos
    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;
//En esta parte tambien le mandamos ese facturaMapper
    public FacturaServiceImpl(final FacturaRepository facturaRepository, final FacturaMapper facturaMapper) {
        this.facturaRepository = facturaRepository;
        this.facturaMapper = facturaMapper;
    }


    @Override
//   Metodo generado por el GenericService
    public List<FacturaDTO> findAll() throws ServiceException {
        try {
//           Almacenamos nuestra lista y ocupamos la QUERY de repository que se llama findAllCustom para hacer nuestro filro
            List<FacturaEntity> lstEntity= facturaRepository.findAllCustom();
//           Hacemos una lista para los DTO
            List<FacturaDTO> lstDTO=new ArrayList<>();
//          Hacemos la lista temporal y le agreagmos el facturaMapperc con tu ClinteEntity que lo pasa a DTO
            for (FacturaEntity clienteEntity : lstEntity) {
                lstDTO.add(facturaMapper.toDto(clienteEntity));
            }
//            Retornamos lista del dto
            return lstDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<FacturaDTO> findByLikeObject(FacturaDTO facturaDTO) throws ServiceException {
        return null;
    }

    @Override
    //   Metodo generado por el GenericService
    public FacturaDTO save(FacturaDTO facturaDTO) throws ServiceException {
        try {
//  Hacemos el maepeo del DTO a un entity y lo guardamos
            FacturaEntity facturaEntity = facturaMapper.toEntity(facturaDTO);
// Al tener ya nuestro mapeo lo guardamos con el save en el repository
            FacturaEntity facturaSave = facturaRepository.save(facturaEntity);
// Creamos este mapeo para mostrarlo al momento de hacer el registro
            FacturaDTO savedFacturaDTO = facturaMapper.toDto(facturaSave);

            return savedFacturaDTO;

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public FacturaDTO update(FacturaDTO facturaDTO) throws ServiceException {
        return null;
    }

    @Override
    //   Metodo generado por el GenericService
    public void delete(Integer id) throws ServiceException {
        try{
//            llamamos al repository y  colocamos la Query que hace el borrado logico en la BD
            facturaRepository.deleteCustom(id);
        }catch (Exception  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<FacturaDTO> findById(Integer id) throws ServiceException {
       try {
           Optional<FacturaEntity> facturaEntityId = facturaRepository.findById(id);
           return facturaEntityId.map(facturaMapper::toDto);
       }catch (Exception e) {
           throw new ServiceException(e);
       }
    }
}
