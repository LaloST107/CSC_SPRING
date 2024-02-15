package centro_soluciones.clicksoft.service.Impl;

import centro_soluciones.clicksoft.entity.Role;
import centro_soluciones.clicksoft.entity.User;
import centro_soluciones.clicksoft.repository.UserRepository;
import centro_soluciones.clicksoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user){

        user.setNombreUsuario((user.getNombreUsuario()));
        user.setApellidoPaterno((user.getApellidoPaterno()));
        user.setApellidoMaterno((user.getApellidoMaterno()));
        user.setCorreoUsuario((user.getCorreoUsuario()));

        user.setPasswordUsuario(passwordEncoder.encode(user.getPasswordUsuario()));
        user.setRolUsuario(Role.Administrador);

        return userRepository.save(user);
    }

    @Override
    public Optional<User>findByUsername(String nombreUsuario){

        return userRepository.findByNombreUsuario(nombreUsuario);
    }

//    @Transactional
    @Override
    public void changeRole(String newRole, String nombreUsuario){
//        System.out.println("Nuevo rol: " + newRole.toString());
//        userRepository.updateUserRole(nombreUsuario, newRole);

        try {
            System.out.println("Nuevo rol: " + newRole.toString());
            String rolAsString = newRole.toString();
            userRepository.updateUserRole(nombreUsuario, rolAsString);
//            userRepository.updateUserRole(nombreUsuario, newRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
