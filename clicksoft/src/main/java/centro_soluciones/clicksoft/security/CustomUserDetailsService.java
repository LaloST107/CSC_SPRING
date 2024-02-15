package centro_soluciones.clicksoft.security;

import centro_soluciones.clicksoft.entity.User;
import centro_soluciones.clicksoft.service.UserService;
import centro_soluciones.clicksoft.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService   implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        busqueda del usuario para después setearlo

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado"+username));


        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRolUsuario().name()));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .nombreUsuario(username)
                .passwordUsuario(user.getPasswordUsuario())
                .authorities(authorities)
                .build();
    }
}
