package centro_soluciones.clicksoft.repository;

import centro_soluciones.clicksoft.entity.ReporteFacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ReporteFacturaRepository extends JpaRepository<ReporteFacturaEntity, Integer> {

    @Query("Select p from ReporteFacturaT p where p.registroActivo = 1")
    List<ReporteFacturaEntity> findAllCustom();

    @Modifying
    @Query(nativeQuery = true, value = "update REPORTE_TICKET set REGISTRO_ACTIVO = '0' where ID_REPORTE = :id")
    void deleteCustom(@Param("id") Integer id);

    @Query("Select f from ReporteFacturaT f where upper(f.nombreReporte) like concat('%', upper(:nombreReporte), '%') and f.registroActivo = 1")
    List<ReporteFacturaEntity> buscarNombre(@Param("nombreReporte") String nombreReporte);
    //List<ReporteFacturaEntity> buscarNombre(@Param(value = "kw") String keyword);
}//end class
