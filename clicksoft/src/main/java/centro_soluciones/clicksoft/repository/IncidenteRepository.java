package centro_soluciones.clicksoft.repository;

import centro_soluciones.clicksoft.entity.IncidenteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface IncidenteRepository extends JpaRepository<IncidenteEntity, Integer> {

    // Sentencias JPQL

    //Sentencia para obtenr todos los incidentes que tengan un registro activo de 1 (Esto se implementa ya que el 0 se utiliza para un borrado logico)
    @Query("Select p from IncidenteC p where p.registroActivo = 1 ")
    List<IncidenteEntity> findAllCustom();

    // Sentencias SQL

    // Sentencia nativa de la base de datos que se utilizara para hacer un borrado logico mediante el Id, actualizando el registro activo a 0
    @Modifying
    @Query(nativeQuery = true, value = "update INCIDENTE set REGISTRO_ACTIVO='0' where ID_INCIDENTE=:id ")
    // Se le indica que recibe un parametro de tipo Integer que sera el ID del Incidente
    void deleteCustom(@Param("id") Integer id);

}
