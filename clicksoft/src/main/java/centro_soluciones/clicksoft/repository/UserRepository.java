package centro_soluciones.clicksoft.repository;

import centro_soluciones.clicksoft.entity.Role;
import centro_soluciones.clicksoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByNombreUsuario(String nombreUsuario);
    @Modifying
    @Query(nativeQuery = true, value = "update USUARIOS set ROL_USUARIO=:rolUsuario where NOMBRE_USUARIO=:nombreUsuario")
    void updateUserRole(@Param("nombreUsuario")  String nombreUsuario, @Param("rolUsuario") String rolUsuario);
}
