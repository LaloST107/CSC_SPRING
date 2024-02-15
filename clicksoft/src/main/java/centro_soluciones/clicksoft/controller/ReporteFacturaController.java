package centro_soluciones.clicksoft.controller;


import centro_soluciones.clicksoft.dto.ReporteFacturaDTO;
import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;
import centro_soluciones.clicksoft.service.ReporteFacturaService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reporteFactura")
@CrossOrigin("http://localhost:4200/")
public class ReporteFacturaController {

    private static final Logger logger = LoggerFactory.getLogger(ReporteFacturaController.class);

    private final ReporteFacturaService reporteFacturaService;

    public ReporteFacturaController(ReporteFacturaService reporteFacturaService) {
        this.reporteFacturaService = reporteFacturaService;
    }

    @GetMapping
    public List<ReporteFacturaDTO> getAllReportes() throws ServiceException {
        return reporteFacturaService.findAll();
    }

    @PostMapping("/insertarReporte")
    public ReporteFacturaDTO insertarReporte(@RequestBody ReporteFacturaDTO reporteFacturaDTO) throws ServiceException {
        return reporteFacturaService.save(reporteFacturaDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Object>> getById(@PathVariable Integer id) throws ServiceException {
        Optional<Object> reporteFactura = Optional.ofNullable(this.reporteFacturaService.findById(id));
        return ResponseEntity.ok(reporteFactura);
    }

    @DeleteMapping("/{id}")
    public void deleteReporte(@PathVariable Integer id) throws ServiceException {
        reporteFacturaService.delete(id);
    }

    @GetMapping("/reporte")
    public List<ReporteFacturaDTO> buscarReporte(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return reporteFacturaService.getNombreReporte("%"+keyword+"%");
    }


//    public ReporteFacturaEntity<String> updateReporte(@PathVariable Integer id, @RequestBody ReporteFacturaDTO reporteFacturaDTO) {
//        ReporteFacturaDTO updateReporteDTO = reporteFacturaService.update(id,reporteFacturaDTO);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteFacturaDTO> update(@PathVariable Integer id, @RequestBody ReporteFacturaDTO reporteFacturaDTO) {
        try {
            reporteFacturaDTO.setId(id);

            ReporteFacturaDTO updateReporte = reporteFacturaService.update(reporteFacturaDTO);

            if (updateReporte != null) {
                return new ResponseEntity<>(updateReporte, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}//end class
