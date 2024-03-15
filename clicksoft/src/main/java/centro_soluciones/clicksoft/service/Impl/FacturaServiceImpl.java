package centro_soluciones.clicksoft.service.Impl;


import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.entity.FacturaEntity;
import centro_soluciones.clicksoft.entity.IncidenteEntity;
import centro_soluciones.clicksoft.mappers.FacturaMapper;
import centro_soluciones.clicksoft.repository.FacturaRepository;
import centro_soluciones.clicksoft.service.FacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        try{
            List<FacturaEntity> facturaEntities = facturaRepository.findByLikeNombre(facturaDTO.getCategoriaFacturacion());
            List<FacturaDTO> facturaDTOS = facturaEntities.stream().map(factura-> facturaMapper.toDto(factura)).collect(Collectors.toList());
            return facturaDTOS;
        }catch (Exception e) {
            throw new ServiceException(e);
        }
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
        try {
            // Es el formato de fecha con el que se actualiza el campo de fecha incidente
            String formatoFecha = "yyyy-MM-dd HH:mm";
            Optional<FacturaEntity> FacturaUpdate =  facturaRepository.findById(facturaDTO.getId());

            if(FacturaUpdate.isPresent()){
                // Obtiene los datos de la entidad que se quieren actualizar y les asigna ese nuevo valor
                FacturaEntity facturaEntity = FacturaUpdate.get();
                facturaEntity.setFolio(facturaDTO.getFolio());
                facturaEntity.setStatus(facturaDTO.getStatus());
                facturaEntity.setCasoReportado(facturaDTO.getCasoReportado());
                facturaEntity.setCategoriaSolucion(facturaDTO.getCategoriaSolucion());
                facturaEntity.setCategoriaProblema(facturaDTO.getCategoriaProblema());
                facturaEntity.setCorreoCliente(facturaDTO.getCorreoCliente());
                facturaEntity.setNumeroFactura(facturaDTO.getNumeroFactura());
                facturaEntity.setFolioVenta(facturaDTO.getFolioVenta());
                facturaEntity.setCategoriaFacturacion(facturaDTO.getCategoriaFacturacion());

                FacturaEntity facturaSave = facturaRepository.save(facturaEntity);
                return facturaMapper.toDto(facturaSave);
            }else {
                throw new ServiceException("No se encontró la factura con el ID: " + facturaDTO.getId());
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    @Override
    //   Metodo generado por el GenericService
    public void delete(Integer id) throws ServiceException {


        try{
//            Busacmos una factura por el ID de manera Optional
            Optional<FacturaEntity> facturaOptional = facturaRepository.findById(id);
//            Evaluamos el caso de que este o no este presente el ID que vamos a utilizar
            if (facturaOptional.isPresent()) {
//                Obtenemos los atrivutos de la factura, en este caso ocupamos el status para poder verificar que tenga su status en Caso Cerrado
                FacturaEntity facturaEntity = facturaOptional.get();
                //Con el equals evaluamos los stastus entrantes de la BD junto con la condición de Caso_Cerrado
//                if (!("Caso_Cerrado".equals(statusFactura.getStatus()) || "Solucionado".equals(statusFactura.getStatus()))) {

//                NOTA de nuevo no quuiere eliminar cuando es Caso cerrado
//                if (!"Caso_Cerrado".equals(facturaEntity.getStatus())) {
//                    throw new ServiceException("No se puede eliminar una factura cuyo estado no sea cerrado");
//                } else {
//
//                    facturaRepository.deleteCustom(id);
//
//                }
                if ("Caso_Cerrado".equals(facturaEntity.getStatus())) {
                    facturaRepository.deleteCustom(id);
                } else {
                    throw new ServiceException("No se puede eliminar una factura cuyo estado no sea cerrado");
                }
            } else {
                throw new ServiceException("No se encontró la factura con el ID proporcionado: " + id);
            }
//            facturaRepository.deleteCustom(id);
        } catch (DataAccessException e) {
            // Manejar la excepción de acceso a datos de manera adecuada
            throw new ServiceException("Error al acceder a la base de datos al intentar eliminar la factura con ID: " + id, e);
        } catch (Exception e) {
            // Manejar otras excepciones de manera general
            throw new ServiceException("Se produjo un error al intentar eliminar la factura con ID: " + id, e);
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



    @Override
    //   Metodo propio de la factura para poder actualizar el dia de solucion, además de su status solucionado
    public FacturaDTO updateDateSolution(Integer id) throws ServiceException {
//        Formato de para la fcha de solución
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            // Obtener la factura de la base de datos por su ID
            FacturaEntity facturaEntity = facturaRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Factura no encontrada con el ID proporcionado"));

            // Obtener la fecha actual
            Date fechaActual = new Date();

            // Formatear la fecha actual al formato
            String fechaActualFormateada = formato.format(fechaActual);

            // Convertir la cadena formateada a un objeto Date
            Date fechaSolucion = formato.parse(fechaActualFormateada);

            // Actualizar la fecha de solución de la factura
            facturaEntity.setFechaSolucion(fechaSolucion);
            facturaEntity.setStatus("Solucionado");
            // Guardar la factura actualizada en la base de datos
            facturaRepository.save(facturaEntity);

            // Crear un nuevo FacturaDTO con los datos actualizados y devolverlo
            FacturaDTO facturaDTO = new FacturaDTO();
            facturaDTO.setId(id);
            facturaDTO.setFechaSolucion(fechaActualFormateada);
            return facturaDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public FacturaDTO updateDateClose(Integer id) throws ServiceException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            // Obtener la factura de la base de datos por su ID
            FacturaEntity facturaEntity = facturaRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Factura no encontrada con el ID proporcionado"));

            // Obtener la fecha actual
            Date fechaActual = new Date();

            // Formatear la fecha actual al formato
            String fechaActualFormateada = formato.format(fechaActual);

            // Convertir la cadena formateada a un objeto Date
            Date fechaCierre = formato.parse(fechaActualFormateada);

            // Actualizar la fecha de cierre y el estado de la factura
            facturaEntity.setFechaCierre(fechaCierre);
            facturaEntity.setStatus("Caso_Cerrado");

            // Guardar la factura actualizada en la base de datos
            facturaRepository.save(facturaEntity);

            // Crear un nuevo FacturaDTO con los datos actualizados y devolverlo
            FacturaDTO facturaDTO = new FacturaDTO();
            facturaDTO.setId(id);
            facturaDTO.setFechaCierre(fechaActualFormateada);
            facturaDTO.setStatus("Cerrado");
            return facturaDTO;
        } catch (ParseException e) {
            throw new ServiceException("Error al parsear la fecha", e);
        } catch (DataAccessException e) {
            // Manejar la excepción de acceso a datos de manera adecuada
            throw new ServiceException("Error al acceder a la base de datos al intentar actualizar la factura con ID: " + id, e);
        } catch (Exception e) {
            // Manejar otras excepciones de manera general
            throw new ServiceException("Se produjo un error al intentar actualizar la factura con ID: " + id, e);
        }
    }

    }



