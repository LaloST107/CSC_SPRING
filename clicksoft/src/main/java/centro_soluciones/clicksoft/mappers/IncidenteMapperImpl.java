package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.IncidenteDTO;
import centro_soluciones.clicksoft.entity.IncidenteEntity;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
// La clase implementa la interface en la que se declaran los metodos para mapear
public class IncidenteMapperImpl implements IncidenteMapper{

    // Formato de Dia/Mes/Año Hora:Minutos:Segundos
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    @Override
    // Aqui se mapea una entididad a un DTO
    public IncidenteDTO toDto(IncidenteEntity e) {
        // Aqui se hace un mapeo manual, llamamos al IncidenteDTO y se le pone un builder para iniciar la construccion del mapeo
        return  IncidenteDTO.builder()
                // Se obtienen los campos para mostrarlos en mediante Postman y frontend
                .id(e.getId())
                .nombreIncidente(e.getNombreIncidente())
                .descripcionIncidente(e.getDescripcionIncidente())
                // La fecha se convierte a string y se le da un formato
                .fechaIncidente(String.valueOf(e.getFechaIncidente()).formatted(formato))
                .direccionImagenIncidente(e.getDireccionImagenIncidente())
                .status(e.getStatus())
                .impacto(e.getImpacto())
                .urgencia(e.getUrgencia())
                .prioridad(e.getPrioridad())
                .prioridadExtra(e.getPrioridadExtra())
                .usuario(e.getUsuario())
                .build();
    }

    @Override
    // Se mapea un DTO a una Entity que servira para guardar los campos en la base de datos
    public IncidenteEntity toEntity(IncidenteDTO dto){

        //Se crea un objeto Date para poder asiganarle un valor y guardarlo
        Date formatoDate = new Date();

        // Se cre un objeto de IncidenteEntity para guardar los campos
        IncidenteEntity incidenteEntity = new IncidenteEntity();

        // Se hace un mapeo manual
        incidenteEntity.setId(dto.getId());
        incidenteEntity.setNombreIncidente(dto.getNombreIncidente());
        incidenteEntity.setDescripcionIncidente(dto.getDescripcionIncidente());
        // A la fecha se le pasa el formatoDate creado para guardar la fecha en la base de datos
        incidenteEntity.setFechaIncidente(formatoDate);
        incidenteEntity.setDireccionImagenIncidente(dto.getDireccionImagenIncidente());
        incidenteEntity.setStatus(dto.getStatus());
        incidenteEntity.setImpacto(dto.getImpacto());
        incidenteEntity.setUrgencia(dto.getUrgencia());
        incidenteEntity.setPrioridad(dto.getPrioridad());
        incidenteEntity.setPrioridadExtra(dto.getPrioridadExtra());
        incidenteEntity.setRegistroActivo(1);
        incidenteEntity.setUsuario(dto.getUsuario());

        return incidenteEntity;
    }
}
