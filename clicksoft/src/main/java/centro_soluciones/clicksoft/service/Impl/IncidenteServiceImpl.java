package centro_soluciones.clicksoft.service.Impl;

import centro_soluciones.clicksoft.dto.IncidenteDTO;
import centro_soluciones.clicksoft.entity.IncidenteEntity;
import centro_soluciones.clicksoft.mappers.IncidenteMapper;
import centro_soluciones.clicksoft.repository.IncidenteRepository;
import centro_soluciones.clicksoft.service.IncidenteService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenteServiceImpl implements IncidenteService {

    // Se hace una inyeccion por constructor en donde se le manda el repositorio y el mapper del Incidente para poder mapearlos
    private final IncidenteRepository incidenteRepository;

    // Se le manda los metodos para poder mapear las entidades y DTO's
    private final IncidenteMapper incidenteMapper;

    public IncidenteServiceImpl(final IncidenteRepository incidenteRepository, IncidenteMapper incidenteMapper) {
        this.incidenteRepository = incidenteRepository;
        this.incidenteMapper = incidenteMapper;
    }

    @Override
    public List<IncidenteDTO> findAll() throws ServiceException {
        try {
            // Se almacena la lista y se utiliza una QUERY del repository (findAllCustom();) para hacer un fitro
            List<IncidenteEntity> lstEntity = incidenteRepository.findAllCustom();

            // Se hace una lista para los DTO's
            List<IncidenteDTO> lstDTO = new ArrayList<>();

            // Se hace una lista temporal y se le agregar el incidenteMapper con el cliente entity para convertirlo a DTO
            for (IncidenteEntity clienteEntity : lstEntity) {
                lstDTO.add(incidenteMapper.toDto(clienteEntity));
            }
            // Se retorna la lista del DTO
            return lstDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<IncidenteDTO> findByLikeObject(IncidenteDTO incidenteDTO) throws ServiceException {
        return null;
    }

    @Override
    public IncidenteDTO save(IncidenteDTO incidenteDTO) throws ServiceException {
        try {
            // Se hace un mapeo del DTO a una entity y se guarda
            IncidenteEntity incidenteEntity = incidenteMapper.toEntity(incidenteDTO);

            // Una vez hecho el mape se guarda con el Save en la base de datos con ayuda del repository
            IncidenteEntity incidenteSave = incidenteRepository.save(incidenteEntity);

            // Se mapea a un DTO para mostrar los campos registrados de la entidad Incidente
            IncidenteDTO savedIncidenteDTO = incidenteMapper.toDto(incidenteSave);

            return savedIncidenteDTO;

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public IncidenteDTO update(IncidenteDTO incidenteDTO) throws ServiceException {
        try {
            // Es el formato de fecha con el que se actualiza el campo de fecha incidente
            String formatoFecha = "yyyy-MM-dd HH:mm";
            Optional<IncidenteEntity> IncidenteEntityUpdt = incidenteRepository.findById(incidenteDTO.getId());

            // Aqui verifica si la entidad que se quiere actualizar existe
            if (IncidenteEntityUpdt.isPresent()) {
                // Obtiene los datos de la entidad que se quieren actualizar y les asigna ese nuevo valor
                IncidenteEntity incidenteEntity = IncidenteEntityUpdt.get();
                incidenteEntity.setNombreIncidente(incidenteDTO.getNombreIncidente());
                incidenteEntity.setDescripcionIncidente(incidenteDTO.getDescripcionIncidente());
                incidenteEntity.setFechaIncidente(new SimpleDateFormat(formatoFecha).parse(incidenteDTO.getFechaIncidente()));
                incidenteEntity.setDireccionImagenIncidente(incidenteDTO.getDireccionImagenIncidente());
                incidenteEntity.setStatus(incidenteDTO.getStatus());
                incidenteEntity.setImpacto(incidenteDTO.getImpacto());
                incidenteEntity.setUrgencia(incidenteDTO.getUrgencia());
                incidenteEntity.setPrioridad(incidenteDTO.getPrioridad());
                incidenteEntity.setPrioridadExtra(incidenteDTO.getPrioridadExtra());
                incidenteEntity.setUsuario(incidenteDTO.getUsuario());

                // Guarda la entidad de incidente con los nuevos valores asignados
                IncidenteEntity incidenteSave = incidenteRepository.save(incidenteEntity);

                return incidenteMapper.toDto(incidenteSave);
            } else {
                throw new ServiceException("No se encontró el incidente con el ID: " + incidenteDTO.getId());
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try{
            // Se hace una invocacion al repository para llamar al metodo de la QUERY y hacer un borrado logico mediante el ID
            incidenteRepository.deleteCustom(id);
        } catch (Exception  e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<IncidenteDTO> findById(Integer id) throws ServiceException {
        try {
            // Se invoca el repository y se llama al metodo de FindByID para encontrar un Incidente mediante su ID
            Optional<IncidenteEntity> incidenteEntityId = incidenteRepository.findById(id);

            // Regresa la entidad encontrada por el ID y la convierte en un DTO
            return incidenteEntityId.map(incidenteMapper::toDto);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
