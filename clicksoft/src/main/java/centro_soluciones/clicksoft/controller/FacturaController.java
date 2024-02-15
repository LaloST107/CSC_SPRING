package centro_soluciones.clicksoft.controller;

import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.service.FacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/facturas")
@CrossOrigin("http://localhost:4200")
public class FacturaController {

 private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaDTO> findAll() throws ServiceException {
        try {
            return facturaService.findAll();
            } catch (ServiceException e) {
            e.printStackTrace();
            return Collections.emptyList();

        }

    }

    @PostMapping("/save")
    public FacturaDTO save(@RequestBody FacturaDTO facturaDTO) throws ServiceException {

        return facturaService.save(facturaDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) throws  ServiceException{
        facturaService.delete(id);
    }

    @GetMapping("/findFactura/{id}")
    public ResponseEntity<FacturaDTO>findFacturaById(@PathVariable Integer id) throws ServiceException {

        Optional<FacturaDTO> FacturaId = facturaService.findById(id);

        return FacturaId.map(factura -> new ResponseEntity<>(factura, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }





}
