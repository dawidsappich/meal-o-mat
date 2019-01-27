package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Entities.UserComment;
import de.sappich.mealomat.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/usercomment")
    public void createUserComment(@RequestBody UserComment userComment) {
        LOGGER.info(userComment.toString());
        User user = userComment.getUser();
        User foundUser = this.userRepository.findById(user.getId()).get();
        LOGGER.info("found user:" + foundUser);
        foundUser.setName(user.getName());
        foundUser.setIsActive(user.getIsActive());
        LOGGER.info("changed user:" + foundUser);
    }
}
