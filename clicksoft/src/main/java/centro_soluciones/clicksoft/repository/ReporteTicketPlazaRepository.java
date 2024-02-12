package centro_soluciones.clicksoft.repository;

import centro_soluciones.clicksoft.entity.FacturaEntity;
import centro_soluciones.clicksoft.entity.ReporteTicketPlazaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface ReporteTicketPlazaRepository extends JpaRepository<ReporteTicketPlazaEntity, Integer> {

    //Query para mostrar todos los tickets con status liberado y numero de registro = 1
    @Query("Select p from TicketEntity p where p.status='Liberado' AND p.numRegistroAct = 1 ")
    List<ReporteTicketPlazaEntity> findAllCustom();

    //Query para realizar borrado logico. Manda registro activo a valor = 0
    @Modifying
    @Query(nativeQuery = true, value = "update TICKET_PLAZA set REGISTRO_ACTIVO='0' where ID_TICKET=:id ")
    void deleteCustom(@Param("id") Integer id);
}
