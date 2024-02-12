package centro_soluciones.clicksoft.controller;

import centro_soluciones.clicksoft.dto.IncidenteDTO;
import centro_soluciones.clicksoft.service.IncidenteService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/incidentes")
public class IncidenteController {

    private final IncidenteService incidenteService;

    public IncidenteController(IncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    // Metodo para obtener todos los incidentes
    @GetMapping
    public ResponseEntity<List<IncidenteDTO>> findAll() {
        try {
            List<IncidenteDTO> incidentes = incidenteService.findAll();
            return new ResponseEntity<>(incidentes, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para salvar registrar un nuevo incidente
    @PostMapping("/save")
    public ResponseEntity<IncidenteDTO> save(@RequestBody IncidenteDTO incidenteDTO) {
        try {
            IncidenteDTO savedIncidente = incidenteService.save(incidenteDTO);
            return new ResponseEntity<>(savedIncidente, HttpStatus.CREATED);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para actualizar los campos de un incidente mediante su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<IncidenteDTO> update(@PathVariable Integer id, @RequestBody IncidenteDTO incidenteDTO) {
        try {
            incidenteDTO.setId(id);

            IncidenteDTO updateIncidente = incidenteService.update(incidenteDTO);
            if (updateIncidente != null) {
                return new ResponseEntity<>(updateIncidente, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para eliminar un incidente mediante su id (Su borrado es logico, no fisico)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            incidenteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para obtener las datos de un incidente mediante su ID
    @GetMapping("/{id}")
    public ResponseEntity<IncidenteDTO> findById(@PathVariable Integer id) {
        try {
            Optional<IncidenteDTO> incidenteDTOId = incidenteService.findById(id);
            return incidenteDTOId.map(incidenteDTO -> new ResponseEntity<>(incidenteDTO, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
