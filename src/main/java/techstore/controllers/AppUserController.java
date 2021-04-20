package techstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import techstore.models.entities.AppUser;
import techstore.models.enums.UserRole;
import techstore.models.repositories.AppUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Secured("ADMIN")
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/users")
    public List<AppUser> index() {
        return appUserRepository.findAll().stream().map(user -> {
            AppUser userDTO = new AppUser();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setPassword("");
            userDTO.setUserRole(user.getUserRole());
            return userDTO;
        }).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public AppUser get(@PathVariable long id) {
        Optional<AppUser> result = appUserRepository.findById(id);
        return result.orElse(null);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser create(@RequestBody AppUser user) {
        user.setPassword(encodePassword(user.getPassword()));
        return appUserRepository.save(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUser save(@PathVariable long id, @RequestBody AppUser newUser) {
        AppUser dbUser = appUserRepository.findById(id).orElse(null);
        if (dbUser != null) {
            dbUser.setUserName(newUser.getUserName());
            if (!newUser.getPassword().isEmpty()) {
                dbUser.setPassword(encodePassword(newUser.getPassword()));
            }
            dbUser.setUserRole(newUser.getUserRole());
            return appUserRepository.save(dbUser);
        } else {
            return null;
        }
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        appUserRepository.deleteById(id);
    }

    @GetMapping("/roles")
    public List<UserRole> roles() {
        return Arrays.asList(UserRole.USER, UserRole.ADMIN);
    }


    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
