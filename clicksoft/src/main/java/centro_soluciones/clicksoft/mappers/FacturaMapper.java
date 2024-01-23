package centro_soluciones.clicksoft.mappers;

import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.entity.FacturaEntity;


//Al declarar nuestros mapeos debe de ser una interface
public interface FacturaMapper {
//Declaramos nuestros metodos para poder mapearlos de Entity a DTO y de DTO a Entity

// Convertimos a DTO
    FacturaDTO toDto(FacturaEntity e);
//Convertimos a Entity
    FacturaEntity toEntity(FacturaDTO facturaDTO);

}
