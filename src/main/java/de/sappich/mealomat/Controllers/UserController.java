package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Entities.UserComment;
import de.sappich.mealomat.Repositories.UserCommentRepository;
import de.sappich.mealomat.Repositories.UserRepository;
import de.sappich.mealomat.Utils.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * GET  user/ping       cheep health check
 * GET  user/{userid}   get infos about user with provided userid
 * POST user/comment    create a comment
 */
@RequestMapping("user")
@RestController
@Api(value = "/user", description = "Endpoint to retrieve infos about exisiting users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCommentRepository userCommentRepository;

    @GetMapping("ping")
    @ApiOperation(value = "check if controller is alive", response = String.class)
    public String pong() {
        return String.format("[%s] works", this.getClass().getName());
    }

    @GetMapping("{userId}")
    @ApiOperation(value = "find existing user by userid", response = User.class)
    public User findUserById(@PathVariable Long userId) {
        Optional<User> foundUser = this.userRepository.findById(userId);
        return foundUser.orElse(new User());
    }

    @PostMapping("comment")
    @ApiOperation(value = "check if controller is alive", response = ApplicationResponse.class)
    public UserComment createUserComment(@RequestBody UserComment userComment) {
        return this.userCommentRepository.save(userComment);
    }
}
