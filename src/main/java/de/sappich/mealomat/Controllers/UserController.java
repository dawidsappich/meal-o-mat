package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Entities.UserComment;
import de.sappich.mealomat.Repositories.UserCommentRepository;
import de.sappich.mealomat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * GET  user/ping       cheep health check
 * GET  user/{userid}   get infos about user with provided userid
 * POST user            create a user
 * POST user/comment    create a comment
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @GetMapping("ping")
    public String pong() {
        return String.format("[%s] works", this.getClass().getName());
    }

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
