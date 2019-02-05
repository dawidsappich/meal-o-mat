package de.sappich.mealomat.Controllers;

import de.sappich.mealomat.Entities.Authority;
import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Repositories.UserRepository;
import de.sappich.mealomat.Utils.ApplicationCode;
import de.sappich.mealomat.Utils.ApplicationResponse;
import de.sappich.mealomat.Services.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "/", description = "REST for registering new users. CSRF is disabled for this route")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("register")
    @ApiOperation(value = "register user", response = ApplicationResponse.class)
    public ResponseEntity<ApplicationResponse> createUser(@RequestBody User user) {

        // make sure the email is not already registered
        Optional<User> foundUser = this.userRepository.findByEmailIgnoreCase(user.getEmail());


        if (foundUser.isPresent()) {

            // create response
            ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
            ApplicationResponse response = builder.setCode(ApplicationCode.ERROR)
                    .setIsSuccess(false)
                    .setMessage("user already exists!")
                    .setTime(LocalDateTime.now())
                    .build();

            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(response);
        }


        Authority role_user = new Authority("ROLE_USER");
        // every user is set to active
        user.setIsActive(true);
        // every registered user has the role USER
        user.setAuthorities(List.of(role_user));
        // persist user
        this.userDetailsService.saveUser(user.getEmail(), user.getPassword(), role_user);

        // create response
        ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
        ApplicationResponse response = builder.setTime(LocalDateTime.now())
                .setMessage("user created")
                .setIsSuccess(true)
                .setCode(ApplicationCode.OK)
                .build();

        return ResponseEntity.ok(response);
    }

}
