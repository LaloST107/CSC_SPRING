package centro_soluciones.clicksoft.controller;

import centro_soluciones.clicksoft.dto.ReporteTicketPlazaDTO;
import centro_soluciones.clicksoft.service.Impl.ReporteTicketPlazaImpl;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReporteTicketPlazaController {

    @Autowired
    private ReporteTicketPlazaImpl reporteTicketPlazaImpl;

    //función para encontrar todos los tickets
    @GetMapping(value = "/reportes")
    public List<ReporteTicketPlazaDTO> findAllTickets() throws ServiceException {
        return reporteTicketPlazaImpl.findAll();
    }

    //función para crear un ticket
    @PostMapping("/crearTicket")
    public ResponseEntity<ReporteTicketPlazaDTO> crearTicket(@RequestBody ReporteTicketPlazaDTO reporteTicketPlazaDTO) throws ServiceException {

        ReporteTicketPlazaDTO ticketCreado = reporteTicketPlazaImpl.save(reporteTicketPlazaDTO);
        return new ResponseEntity<>(ticketCreado, HttpStatus.CREATED);
    }

    //función para actualizar un ticket
    @PutMapping("/updateTicket")
    public ResponseEntity<ReporteTicketPlazaDTO> updateTicket(@RequestBody ReporteTicketPlazaDTO reporteTicketPlazaDTO) throws ServiceException {

        try {
            ReporteTicketPlazaDTO ticketActualizado = reporteTicketPlazaImpl.update(reporteTicketPlazaDTO);
            return new ResponseEntity<>(ticketActualizado, HttpStatus.OK);
        } catch (ServiceException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //función para eliminar un ticket
    @DeleteMapping("/deleteTicket/{ticketId}")
    public void eliminarTicket(@PathVariable("ticketId") Integer id) throws ServiceException {
        reporteTicketPlazaImpl.delete(id);
    }

    //función para encontrar un ticket por Id
    @GetMapping("/findTicket/{id}")
    public ResponseEntity<ReporteTicketPlazaDTO> findTicketById(@PathVariable Integer id) throws ServiceException {

        Optional<ReporteTicketPlazaDTO> findTicketId = reporteTicketPlazaImpl.findById(id);

        if(findTicketId.isPresent()){
            return new ResponseEntity<>(findTicketId.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
