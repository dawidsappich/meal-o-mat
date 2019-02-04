package de.sappich.mealomat.Services;

import de.sappich.mealomat.Entities.Authority;
import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = this.userRepository.findByEmailIgnoreCase(username);
        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("user not found for %s", username));
        }
        return new CustomUserDetails(foundUser.get());
    }

    public User createUser(String email, String password, Authority ... authorities) {
        User user = new User(email, password, authorities);
        return this.userRepository.save(user);
    }


}
