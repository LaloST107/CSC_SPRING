package centro_soluciones.clicksoft.repository;

import centro_soluciones.clicksoft.entity.FacturaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//Al manejar una query nativa es necesario poner e transactional
@Transactional
@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Integer> {
    //sentencias JPQL

    // Sentencia para obtener las facuturas que tengan su registroActivo en 1 y su status en Abierto
    @Query("Select p from FacturaC p where p.status='Abierto' AND p.registroActivo = 1 ")
    List<FacturaEntity> findAllCustom();

    //sentencias SQL
   //Sentencia nativa de la base de datos que se ocupara para poder hacer el borrado logico por un id y actualiza su registroActivo a 0
    @Modifying
    @Query(nativeQuery = true, value = "update FACTURA set REGISTRO_ACTIVO='0' where ID_FOLIO=:id ")
   //Le indicamos que resive como parametro un id y su id es tipo integer
    void deleteCustom(@Param("id") Integer id);
}
