package de.sappich.mealomat.Services;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User findUser(User userToFind) {
        Optional<User> foundUser = this.userRepository.findById(userToFind.getId());
        return foundUser.get();
    }
}
