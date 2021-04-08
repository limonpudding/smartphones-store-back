package techstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import techstore.models.entities.AppUser;
import techstore.models.repositories.AppUserRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username);

        if(user == null) {
            throw new UsernameNotFoundException("Пользователь не существует");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        // TODO проверка пароля

        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
