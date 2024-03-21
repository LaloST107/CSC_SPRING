package centro_soluciones.clicksoft.controller;

import centro_soluciones.clicksoft.dto.IncidenteDTO;
import centro_soluciones.clicksoft.service.IncidenteService;
import centro_soluciones.clicksoft.service.exception.ServiceException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/incidentes")
@CrossOrigin("http://localhost:4200")
public class IncidenteController {

    private final IncidenteService incidenteService;

    private final String imageDirectory = "src/main/resources/static/images/";

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

    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("imagen") MultipartFile imagen) {
        if (imagen.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "La imagen está vacía"));
        }

        try {
            // Guardar la imagen en una carpeta en el backend
            String uploadsDir = "src/main/resources/static/images"; // Ruta donde se guarda la imagen
            String fileName = imagen.getOriginalFilename();
            String filePath = uploadsDir + "/" + fileName;
            byte[] bytes = imagen.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            return ResponseEntity.ok().body(Collections.singletonMap("message", "Imagen cargada exitosamente"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al cargar la imagen"));
        }
    }

    @GetMapping("/images/{imageName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) {
        try {
            Path imagePath = Paths.get(imageDirectory).resolve(imageName);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // o MediaType.IMAGE_PNG según el tipo de imagen
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
