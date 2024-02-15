package centro_soluciones.clicksoft.dto;

import centro_soluciones.clicksoft.entity.User;
//import centro_soluciones.clicksoft.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncidenteDTO {

    private Integer id;

    private String nombreIncidente;

    private String descripcionIncidente;

    private String fechaIncidente;

    private String direccionImagenIncidente;

    private String status;

    private String impacto;

    private String urgencia;

    private String prioridad;

    private String prioridadExtra;

    private Integer registroActivo;

    private User usuario;
//    private UsuarioEntity usuario;

}
