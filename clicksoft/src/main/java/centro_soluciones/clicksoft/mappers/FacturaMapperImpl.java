package centro_soluciones.clicksoft.mappers;
import centro_soluciones.clicksoft.dto.FacturaDTO;
import centro_soluciones.clicksoft.entity.FacturaEntity;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// creamos la estructura de nuestros metodos para mapear de un entity a un dto y de un dto a un entity
@Component
// la clase se implementa con la interaface en la que se declaran los metodos
public class FacturaMapperImpl implements FacturaMapper{

// Se le da el fomato de fecha mandando el día mes año horas minutos y segundos
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Override
//    En esta parte mapeamos de una entidad a un DTO
    public FacturaDTO toDto(FacturaEntity e) {
//En esta parte lo que hacemos es hacer el mapeo de manera manual, llamamos el FacturaDTO y le ponemos un BUILDER para hacer la construcción del mapeo
                return  FacturaDTO.builder()
//Obtenemos las variables o campos que vamos a ocupar para mostrarlos en el postman
                .id(e.getId())
                .folio(e.getFolio())
                .status(e.getStatus())
                .casoReportado(e.getCasoReportado())
                .categoriaSolucion(e.getCategoriaSolucion())
                .categoriaProblema(e.getCategoriaProblema())
                .correoCliente(e.getCorreoCliente())
                .numeroFactura(e.getNumeroFactura())
                .folioVenta(e.getFolioVenta())
                .categoriaFacturacion(e.getCategoriaFacturacion())
//Para las fechas lo convertimos a string el y le damos su foramto
                .fechaRecepcion(String.valueOf(e.getFechaRecepcion()).formatted(formato))
                .fechaPrimeraAtencion(String.valueOf(e.getFechaPrimeraAtencion()))
                .fechaSolucion(String.valueOf(e.getFechaSolucion()).formatted(formato))
//Para estas fechas realiza una validación de los campos si vienen con informacion entonces que les de el formato si no que los mande como null
                .fechaSolucion(e.getFechaSolucion() != null ? String.valueOf(e.getFechaSolucion()).formatted(formato) : null)
                .fechaCierre(e.getFechaCierre() != null ? String.valueOf(e.getFechaCierre()).formatted(formato) : null)
                .build();

    }
    @Override
//    En esta parte mapeeamos de un DTO a un entity que nos servira para poder guardarla en la base de datos
    public FacturaEntity toEntity(FacturaDTO dto){
//Creamos objeto date para poder setearlo y guardarlo en su campo
        Date formatoDate = new Date();

//Creamos un objeto facturaEntity  para guardar los campos
        FacturaEntity facturaEntity = new FacturaEntity();
//Hacemos el mapeo de manera manual, si es posible tambien podemos poder condiciones al hacer este mapeo
        facturaEntity.setFolio(dto.getFolio());
        facturaEntity.setStatus(dto.getStatus());
        facturaEntity.setCasoReportado(dto.getCasoReportado());
        facturaEntity.setCategoriaSolucion(dto.getCategoriaSolucion());
        facturaEntity.setCategoriaProblema(dto.getCategoriaProblema());
        facturaEntity.setCorreoCliente(dto.getCorreoCliente());
        facturaEntity.setNumeroFactura(dto.getNumeroFactura());
        facturaEntity.setFolioVenta(dto.getFolioVenta());
        facturaEntity.setCategoriaFacturacion(dto.getCategoriaFacturacion());
//Para las fechas le pasamos el formatoDate creado para almacenarlo en la BD
        facturaEntity.setFechaRecepcion(formatoDate);
        facturaEntity.setFechaPrimeraAtencion(null);
//        facturaEntity.setFechaPrimeraAtencion(formatoDate);
//Las demas fechas las dejamos como null ya que las ocuparemos después
        facturaEntity.setFechaSolucion(null);
        facturaEntity.setFechaCierre(null);
        facturaEntity.setRegistroActivo(1);

        return facturaEntity;

    }



}
