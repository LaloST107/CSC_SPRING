package centro_soluciones.clicksoft.service.Impl;

import centro_soluciones.clicksoft.dto.ReporteTicketPlazaDTO;
import centro_soluciones.clicksoft.entity.ReporteTicketPlazaEntity;
import centro_soluciones.clicksoft.mappers.ReporteTicketMImpl;
import centro_soluciones.clicksoft.repository.ReporteTicketPlazaRepository;
import centro_soluciones.clicksoft.service.ReporteTicketPlazaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Service
public class ReporteTicketPlazaImpl implements ReporteTicketPlazaService {

    @Autowired
    private ReporteTicketPlazaRepository reporteTicketPlazaRepository;
    @Autowired
    private ReporteTicketMImpl reporteTicketMapper;
    //private static final Logger logger = LoggerFactory.getLogger(ReporteTicketPlazaImpl.class);

    //función para encontrar todos los tickets
    @Override
    public List<ReporteTicketPlazaDTO> findAll() throws ServiceException {

        List<ReporteTicketPlazaEntity> reportesTicket = reporteTicketPlazaRepository.findAllCustom();

        List<ReporteTicketPlazaDTO> ticketsDTOS = reportesTicket.stream().map(
                ticket -> reporteTicketMapper.toDto(ticket)).collect(Collectors.toList());

        return ticketsDTOS;

    }

    @Override
    public List<ReporteTicketPlazaDTO> findByLikeObject(ReporteTicketPlazaDTO reporteTicketPlazaDTO) throws ServiceException {
        return null;
    }

    //función para crear un ticket
    @Override
    public ReporteTicketPlazaDTO save(ReporteTicketPlazaDTO reporteTicketPlazaDTO) throws ServiceException {

        ReporteTicketPlazaEntity ticketEntity = reporteTicketMapper.toEntity(reporteTicketPlazaDTO);
        ReporteTicketPlazaEntity ticketGuardado = reporteTicketPlazaRepository.save(ticketEntity);

        return reporteTicketMapper.toDto(ticketGuardado);
    }

    //función para actualizar un ticket
    @Override
    public ReporteTicketPlazaDTO update(ReporteTicketPlazaDTO reporteTicketPlazaDTO) throws ServiceException {
        Integer id = reporteTicketPlazaDTO.getIdTicket();

        if(!reporteTicketPlazaRepository.existsById(id)){
            throw new ServiceException("Usuario no encontrado");
        }

        ReporteTicketPlazaEntity ticketExistente = reporteTicketPlazaRepository.getOne(id);

        ticketExistente.setFolioVenta(reporteTicketPlazaDTO.getFolioVenta());
        ticketExistente.setPlaza(reporteTicketPlazaDTO.getPlaza());
        ticketExistente.setTienda(reporteTicketPlazaDTO.getTienda());
        ticketExistente.setStatus(reporteTicketPlazaDTO.getStatus());
        ticketExistente.setEntidad(reporteTicketPlazaDTO.getEntidad());
        //ticketExistente.setFecha();reporteTicketPlazaDTO.getFecha());
        ticketExistente.setSubtotalVenta(reporteTicketPlazaDTO.getSubtotalVenta());
        ticketExistente.setTotalVenta(reporteTicketPlazaDTO.getTotalVenta());
        ticketExistente.setCaja(reporteTicketPlazaDTO.getCaja());
        ticketExistente.setNumTicket(reporteTicketPlazaDTO.getNumTicket());
        ticketExistente.setNumRegistroAct(reporteTicketPlazaDTO.getNumRegistroAct());

        ReporteTicketPlazaEntity ticketActualizado = reporteTicketPlazaRepository.save(ticketExistente);

        return reporteTicketMapper.toDto(ticketActualizado);
    }

    //función para eliminar un ticket
    @Override
    public void delete(Integer id) throws ServiceException {

        try {
            reporteTicketPlazaRepository.deleteCustom(id);
        } catch (Exception  e) {
            throw new ServiceException(e);
        }
    }

    //función para encontrar un ticket por Id
    @Override
    public Optional<ReporteTicketPlazaDTO> findById(Integer id) throws ServiceException {

        Optional<ReporteTicketPlazaEntity> findTicketId = reporteTicketPlazaRepository.findById(id);
        return findTicketId.map(reporteTicketMapper::toDto);
    }
}
