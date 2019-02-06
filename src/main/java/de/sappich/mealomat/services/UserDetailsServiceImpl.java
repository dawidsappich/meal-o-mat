package de.sappich.mealomat.services;

import de.sappich.mealomat.entities.Authority;
import de.sappich.mealomat.entities.User;
import de.sappich.mealomat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = this.userRepository.findByEmailIgnoreCase(username);
        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("user not found for %s", username));
        }
        return new CustomUserDetails(foundUser.get());
    }

    public User saveUser(String email, String password, Authority ... authorities) {
        User user = new User(email, passwordEncoder.encode(password), authorities);
        return this.userRepository.save(user);
    }
}
