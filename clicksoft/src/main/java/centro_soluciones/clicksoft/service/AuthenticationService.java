package centro_soluciones.clicksoft.service;

import centro_soluciones.clicksoft.entity.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
