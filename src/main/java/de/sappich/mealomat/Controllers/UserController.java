package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Entities.UserComment;
import de.sappich.mealomat.Repositories.UserCommentRepository;
import de.sappich.mealomat.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

/**
 * GET  user/{userid}   get infos about user with provided userid
 * POST user            create a user
 * POST user/comment    create a comment
 */
@RequestMapping("user")
@RestController
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @GetMapping("{userId}")
    public User findUserById(@PathVariable Long userId) {
        Optional<User> foundUser = this.userRepository.findById(userId);
        return foundUser.orElse(new User());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PostMapping("comment")
    public UserComment createUserComment(@RequestBody UserComment userComment) {
        return this.userCommentRepository.save(userComment);
    }
}
