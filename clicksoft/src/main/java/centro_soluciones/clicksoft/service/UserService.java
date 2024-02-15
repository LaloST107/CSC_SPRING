package centro_soluciones.clicksoft.service;

import centro_soluciones.clicksoft.entity.Role;
import centro_soluciones.clicksoft.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String nombreUsuario);

//    @Transactional//
    void changeRole(String newRole, String nombreUsuario);
}
