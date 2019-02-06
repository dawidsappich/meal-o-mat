package de.sappich.mealomat.api;

import de.sappich.mealomat.api.viewmodel.UserViewModel;
import de.sappich.mealomat.entities.User;
import de.sappich.mealomat.entities.UserComment;
import de.sappich.mealomat.repositories.UserCommentRepository;
import de.sappich.mealomat.repositories.UserRepository;
import de.sappich.mealomat.utils.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
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

    @GetMapping("{userId}")
    @ApiOperation(value = "find email user by userid", response = String.class)
    public String findEmailById(@PathVariable Long userId) {
        Optional<User> foundUser = this.userRepository.findById(userId);
        return foundUser.isPresent() ? foundUser.get().getEmail() : "not found";
    }

    @PostMapping("comment")
    @ApiOperation(value = "check if controller is alive", response = ApplicationResponse.class)
    public UserComment createUserComment(@RequestBody UserComment userComment) {
        return this.userCommentRepository.save(userComment);
    }
}
