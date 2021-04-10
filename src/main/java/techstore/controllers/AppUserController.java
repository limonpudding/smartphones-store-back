package techstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import techstore.models.entities.AppUser;
import techstore.models.enums.UserRole;
import techstore.models.repositories.AppUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    /**
     * Полный список - GET
     */
    @GetMapping("/users")
    public List<AppUser> index() {
        return appUserRepository.findAll();
    }

    /**
     * Возвращаем одну запись - GET
     * @param id
     */
    @GetMapping("/users/{id}")
    public AppUser get(@PathVariable long id) {
        Optional<AppUser> result = appUserRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Создание новой записи - POST
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser create(@RequestBody AppUser user) {
        user.setPassword(encodePassword(user.getPassword()));
        return appUserRepository.save(user);
    }

    /**
     * Сохранение записи - PUT
     * @param id
     */
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUser save(@PathVariable long id, @RequestBody AppUser newUser) {
        return appUserRepository.findById(id)
            .map(user -> {
                user.setUserName(newUser.getUserName());
                user.setPassword(encodePassword(newUser.getPassword()));
                user.setUserRole(newUser.getUserRole());
                return appUserRepository.save(user);
            })
            .orElse(null);
    }

    /**
     * Удаление записи - DELETE
     * @param id
     */
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        appUserRepository.deleteById(id);
    }

    /**
     * Полный список - GET
     */
    @GetMapping("/roles")
    public List<UserRole> roles() {
        return Arrays.asList(UserRole.USER, UserRole.ADMIN);
    }


    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
