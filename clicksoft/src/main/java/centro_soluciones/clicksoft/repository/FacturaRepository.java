package centro_soluciones.clicksoft.repository;

import centro_soluciones.clicksoft.entity.FacturaEntity;
//import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
//Al manejar una query nativa es necesario poner e transactional
@Transactional
@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Integer> {
    //sentencias JPQL

    // Sentencia para obtener las facuturas que tengan su registroActivo en 1 y su status en Abierto
    @Query("Select p from FacturaC p where p.registroActivo = 1 ")
    List<FacturaEntity> findAllCustom();
//    @Query("SELECT p FROM FacturaC p WHERE (p.status='Abierto' OR p.status='Solucionado') AND p.registroActivo = 1")
//    List<FacturaEntity> findAllCustom();
//    @Query("Select p from FacturaC p where p.registroActivo = 1 ")
//    List<FacturaEntity> findAllCustom();
    //sentencias SQL
   //Sentencia nativa de la base de datos que se ocupara para poder hacer el borrado logico por un id y actualiza su registroActivo a 0
    @Modifying
    @Query(nativeQuery = true, value = "update FACTURA set REGISTRO_ACTIVO='0' where ID_FOLIO=:id ")
   //Le indicamos que resive como parametro un id y su id es tipo integer
    void deleteCustom(@Param("id") Integer id);
//    @Modifying
//    @Query(nativeQuery = true, value = "UPDATE FACTURA SET STATUS = 'Caso_Cerrado', REGISTRO_ACTIVO = '0' WHERE ID_FOLIO = :id")
//    void deleteCustom(@Param("id") Integer id);
//    @Modifying
//    @Query(nativeQuery = true, value = "UPDATE FACTURA SET STATUS = 'Caso_Cerrado', FECHA_SOLUCION = :fechaSolucion, REGISTRO_ACTIVO = '0' WHERE ID_FOLIO = :id")
//    void deleteCustom(@Param("id") Integer id, @Param("fechaSolucion") Date fechaSolucion);

    // Sentencia SQL para gusar el nombre de la factura

    @Query("SELECT f FROM FacturaC f WHERE UPPER(f.categoriaFacturacion) LIKE CONCAT('%', UPPER(:categoriaFacturacion), '%') AND f.registroActivo = 1")
    List<FacturaEntity> findByLikeNombre(@Param("categoriaFacturacion") String categoriaFacturacion);

}
