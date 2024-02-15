package centro_soluciones.clicksoft.service.Impl;

import centro_soluciones.clicksoft.dto.ReporteFacturaDTO;
import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;
import centro_soluciones.clicksoft.mappers.ReporteFacturaMapper;
import centro_soluciones.clicksoft.repository.ReporteFacturaRepository;
import centro_soluciones.clicksoft.service.ReporteFacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
@Slf4j
public class ReporteFacturaServiceImpl implements ReporteFacturaService  {

    private final ReporteFacturaRepository reporteFacturaRepository;

    private final ReporteFacturaMapper reporteFacturaMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReporteFacturaServiceImpl.class);

    public ReporteFacturaServiceImpl(ReporteFacturaRepository reporteFacturaRepository, ReporteFacturaMapper reporteFacturaMapper) {
        this.reporteFacturaRepository = reporteFacturaRepository;
        this.reporteFacturaMapper = reporteFacturaMapper;
    }


    @Override
    public List<ReporteFacturaDTO> findAll() throws ServiceException {
        try {
            List<ReporteFacturaEntity> listaEntity = reporteFacturaRepository.findAllCustom();

            List<ReporteFacturaDTO> listaDTO = new ArrayList<>();

            for (ReporteFacturaEntity reporteFacturaEntity : listaEntity) {
                listaDTO.add(reporteFacturaMapper.toDto(reporteFacturaEntity));
            }
            return listaDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReporteFacturaDTO> findByLikeObject(ReporteFacturaDTO reporteFacturaDTO) throws ServiceException {
        return null;
    }

    @Override
    public List<ReporteFacturaDTO> getNombreReporte(String keyword) {
        List<ReporteFacturaEntity> facturaEntities = reporteFacturaRepository.buscarNombre(keyword);
        List<ReporteFacturaDTO> facturaDTOS = facturaEntities.stream().map(factura -> reporteFacturaMapper.toDto(factura)).collect(Collectors.toList());
        return facturaDTOS;
    }

    @Override
    public ReporteFacturaDTO save(ReporteFacturaDTO reporteFacturaDTO) throws ServiceException {
        try {
            ReporteFacturaEntity reporteFacturaEntity = reporteFacturaMapper.toEntity(reporteFacturaDTO);
            ReporteFacturaEntity reporteFacturaSave = reporteFacturaRepository.save(reporteFacturaEntity);
            ReporteFacturaDTO savedReporteFacturaDTO = reporteFacturaMapper.toDto(reporteFacturaSave);

            logger.info("Error en el metodo: " + reporteFacturaDTO);

            return savedReporteFacturaDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ReporteFacturaDTO update(ReporteFacturaDTO reporteFacturaDTO) throws ServiceException {
        try {
            String formatoFecha = "yyyy-MM-dd";
            Optional<ReporteFacturaEntity> optionalReporteFacturaEntity = reporteFacturaRepository.findById(reporteFacturaDTO.getId());
            if (optionalReporteFacturaEntity.isPresent()) {

                ReporteFacturaEntity reporteFactura = optionalReporteFacturaEntity.get();

                reporteFactura.setNombreReporte(reporteFacturaDTO.getNombreReporte());
                reporteFactura.setDescripcion(reporteFacturaDTO.getDescripcion());
                reporteFactura.setFecha(new SimpleDateFormat(formatoFecha).parse(reporteFacturaDTO.getFecha()));
                reporteFactura.setStatus(reporteFacturaDTO.getStatus());
                reporteFactura.setIdTicket(reporteFacturaDTO.getIdTicket());

                ReporteFacturaEntity update = reporteFacturaRepository.save(reporteFactura);

                return reporteFacturaMapper.toDto(update);
            } else {
                throw new ServiceException("No se encontro el reporte con el id: " + reporteFacturaDTO.getId());
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            reporteFacturaRepository.deleteCustom(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ReporteFacturaDTO> findById(Integer id) throws ServiceException {
//        try {
//            Optional<ReporteFacturaEntity> optionalReporteFacturaEntity = reporteFacturaRepository.findById(id);
//
//            if (optionalReporteFacturaEntity.isPresent()) {
//                List<ReporteFacturaDTO> reporteIdDTO = new ArrayList<>();
//                reporteIdDTO.add(reporteFacturaMapper.toDtoId(optionalReporteFacturaEntity.get()));
//                return Optional.of(reporteIdDTO);
//            } else {
////                throw new ServiceException( String.format("El vendedor con el id= %s no existe"));
//                return Optional.empty();
//            }
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
        return Optional.empty();
    }


}//end class
