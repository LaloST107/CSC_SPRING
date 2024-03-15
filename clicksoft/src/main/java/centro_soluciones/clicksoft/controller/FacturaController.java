package centro_soluciones.clicksoft.controller;

import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.dto.IncidenteDTO;
import centro_soluciones.clicksoft.dto.ReporteFacturaDTO;
import centro_soluciones.clicksoft.entity.FacturaEntity;
import centro_soluciones.clicksoft.service.FacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
@RestController
@RequestMapping("api/facturas")
@CrossOrigin("http://localhost:4200")
public class FacturaController {

 private final FacturaService facturaService;
    private static final Logger logger = LogManager.getLogger(FacturaController.class);
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> findAll() throws ServiceException {
        try {

            List<FacturaDTO> lisFacturas= facturaService.findAll();
            return new ResponseEntity<>(lisFacturas, HttpStatus.OK);
            } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/save")
//    Se guarda una factura por el cuerpo (json)
    public ResponseEntity<FacturaDTO>  save(@RequestBody FacturaDTO facturaDTO) throws ServiceException {
        try {
//            llamamos al metodo de save para poder guardar la facturaDTO
            FacturaDTO saveFatura = facturaService.save(facturaDTO);
//Mostramos un status Httpde created en caso de exito
            return new ResponseEntity<>(saveFatura, HttpStatus.CREATED);
        } catch (ServiceException e) {
            e.printStackTrace();
//            logemos el error en el caso de no poder guardar la factura
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("{id}")
//    se espera una variable por el Path en este caso el ID para poder hacer su eliminación
    public void delete(@PathVariable Integer id, HttpServletResponse response) {
        try {
            //metodo delete que espera el ID del path
            facturaService.delete(id);
            response.setStatus(HttpServletResponse.SC_OK); // 200 OK
        } catch (ServiceException e) {
            // Loguea la excepción para propósitos de depuración
            logger.error("Error al eliminar la factura con ID " + id + ": " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    @GetMapping("/findFactura/{id}")
    public ResponseEntity<FacturaDTO>findFacturaById(@PathVariable Integer id) throws ServiceException {

        Optional<FacturaDTO> FacturaId = facturaService.findById(id);

        return FacturaId.map(factura -> new ResponseEntity<>(factura, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/update-solution/{id}")
    public ResponseEntity<FacturaDTO> updateDateSolution(@PathVariable Integer id) throws ServiceException {
        try {
            FacturaDTO updatedFacturaDTO = facturaService.updateDateSolution(id);
            return ResponseEntity.ok(updatedFacturaDTO);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-Factura")
    public ResponseEntity<?> findByLikeFactura(@RequestParam String categoriaFacturacion) throws ServiceException {
        try {
            List<FacturaDTO> facturas = facturaService
                    .findByLikeObject(FacturaDTO.builder().categoriaFacturacion(categoriaFacturacion).build());
            if (facturas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(facturas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }

    }

//Caso cerrado y fecha de cierre actualizada
    @PutMapping("/update-close/{id}")
    public ResponseEntity<FacturaDTO> updateDateClose(@PathVariable Integer id) throws ServiceException {
        try {
            FacturaDTO updatedFacturaDTO = facturaService.updateDateClose(id);
            return ResponseEntity.ok(updatedFacturaDTO);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<FacturaDTO> updateFactura(@PathVariable Integer id, @RequestBody FacturaDTO facturaDTO) throws ServiceException  {
        try {
            facturaDTO.setId(id);
            FacturaDTO FacturaUpdate  =  facturaService.update(facturaDTO);
            if(FacturaUpdate != null) {
                return new ResponseEntity<>(FacturaUpdate, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
