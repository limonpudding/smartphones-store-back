package techstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import techstore.models.entities.AppUser;
import techstore.models.enums.UserRole;
import techstore.models.repositories.AppUserRepository;

import java.util.Optional;

@Controller
@CrossOrigin
public class AuthController {

    @Autowired
    AppUserRepository appUserRepository;

    @PostMapping(value = "/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserRole login(@RequestBody AppUser user) {
        Optional<AppUser> dbUser = appUserRepository.findByUserName(user.getUserName());
        if (dbUser.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(user.getPassword(), dbUser.get().getPassword())) {
                return dbUser.get().getUserRole();
            }
        }
        return UserRole.GUEST;
    }
}
