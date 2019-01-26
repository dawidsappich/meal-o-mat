package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Entities.UserComment;
import de.sappich.mealomat.Repositories.UserCommentRepository;
import de.sappich.mealomat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
public class UserController {

    private final UserCommentRepository commentRepository;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, UserCommentRepository commentRepository) {
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "/usercomment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody User user) {
        UserComment userComment = new UserComment();
        User foundUser = this.userService.findUser(user);
        userComment.setUser(foundUser);
        userComment.setCommentId(UUID.randomUUID().toString());
        userComment.setContent("created via REST API");
        userComment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        this.commentRepository.save(userComment);
    }
}
