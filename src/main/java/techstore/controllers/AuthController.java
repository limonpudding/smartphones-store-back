package techstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import techstore.models.JwtCredentials;
import techstore.models.entities.AppUser;
import techstore.models.enums.UserRole;
import techstore.models.repositories.AppUserRepository;
import techstore.security.JwtUtils;
import techstore.models.UserDetailsImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
public class AuthController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

//    Базовая авторизация
//    @PostMapping(value = "/login")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public UserRole login(@RequestBody AppUser user) {
//        Optional<AppUser> dbUser = appUserRepository.findByUserName(user.getUserName());
//        if (dbUser.isPresent()) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//            if (encoder.matches(user.getPassword(), dbUser.get().getPassword())) {
//                return dbUser.get().getUserRole();
//            }
//        }
//        return UserRole.GUEST;
//    }
//
//    @PostMapping(value = "/register")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public UserRole register(@RequestBody AppUser user) {
//        user.setUserRole(UserRole.USER);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));
//        return appUserRepository.save(user).getUserRole();
//    }

    @PostMapping("/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JwtCredentials login(@RequestBody AppUser loginRequest) {
        return authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AppUser register(@RequestBody AppUser signUpRequest) {
        if (appUserRepository.existsByUserName(signUpRequest.getUserName())) {
            return null;
        }
        AppUser user = new AppUser(
                signUpRequest.getUserName(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                UserRole.USER
        );

        AppUser userToAuth = appUserRepository.save(user);
        userToAuth.setPassword("");
        return userToAuth;
    }

    private JwtCredentials authenticateUser(AppUser loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtCredentials(userDetails.getId(), userDetails.getUsername(), roles, jwt);
    }
}

