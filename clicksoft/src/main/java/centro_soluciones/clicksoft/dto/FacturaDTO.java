package centro_soluciones.clicksoft.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//JsonInclude nos sirve para quqe al momento de generar nuestro json no obtenga ningun dado null, lo que hace es que los ignora y no los muestra
@JsonInclude(value = Include.NON_NULL)
//Genera un patrón de diseño Builder para la construcción de instancias.
@Builder
//Genera un constructor sin argumentos. Útil cuando se necesita crear instancias vacías de la clase.
@NoArgsConstructor
//Genera un constructor que acepta todos los atributos de la clase como argumentos. Útil para la inicialización completa de instancias al crearlas.
@AllArgsConstructor
@Data
//Construcción de nuestro DTO en el cual podemos hacer cambios ya sea en el tipo de dato o agregar algun nuevo campo
public class FacturaDTO {

   private Integer id;

    private Integer folio;

    private String status;

    private String casoReportado;

    private String categoriaSolucion;

    private String categoriaProblema;

    private String correoCliente;

    private Integer numeroFactura;

    private String folioVenta;

    private String categoriaFacturacion;

    private String fechaRecepcion;

    private String fechaPrimeraAtencion;

    private String fechaSolucion;

    private String fechaCierre;

   private Integer registroActivo;




}
