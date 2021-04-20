package techstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import techstore.models.entities.AppUser;
import techstore.models.enums.UserRole;
import techstore.models.repositories.AppUserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не существует");
        }

        List<SimpleGrantedAuthority> authorities = null;
        if (UserRole.ADMIN.equals(user.getUserRole())) {
            authorities = Arrays.asList(new SimpleGrantedAuthority(UserRole.ADMIN.name()), new SimpleGrantedAuthority(UserRole.USER.name()));
        }

        if (UserRole.USER.equals(user.getUserRole())) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(UserRole.USER.name()));
        }


        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
