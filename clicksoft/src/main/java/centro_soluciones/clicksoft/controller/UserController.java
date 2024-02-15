package centro_soluciones.clicksoft.controller;


import centro_soluciones.clicksoft.entity.Role;
import centro_soluciones.clicksoft.security.UserPrincipal;
import centro_soluciones.clicksoft.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
//
//@PutMapping("change/{role}")
//    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role){
//
//       System.out.println("Valor del rol recibido: " + role.toString() + userPrincipal.getNombreUsuario());
//        userService.changeRole(role, userPrincipal.getNombreUsuario());
//
//        return ResponseEntity.ok(true);
//
//    }


    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable String role){
        try {
//            Role newRole = Role.valueOf(role); // Convierte el string a un valor del enum Role
            System.out.println("Valor del rol recibido: " + role + " " + userPrincipal.getNombreUsuario());
            userService.changeRole(role, userPrincipal.getNombreUsuario());
            return ResponseEntity.ok(true);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Rol inválido: " + role);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al cambiar el rol del usuario.");
        }
    }
}
