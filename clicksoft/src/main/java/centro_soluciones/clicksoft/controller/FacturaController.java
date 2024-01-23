package centro_soluciones.clicksoft.controller;

import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.service.FacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/facturas")
public class FacturaController {

 private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaDTO> findAll() throws ServiceException {
        return facturaService.findAll();
    }

    @PostMapping("/save")
    public FacturaDTO save(@RequestBody FacturaDTO facturaDTO) throws ServiceException {

        return facturaService.save(facturaDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) throws  ServiceException{
        facturaService.delete(id);
    }


}
