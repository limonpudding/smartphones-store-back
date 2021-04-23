package techstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import techstore.models.UserDetailsImpl;
import techstore.models.entities.AppUser;
import techstore.models.repositories.AppUserRepository;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не существует!"));
        return UserDetailsImpl.build(user);
    }
}
