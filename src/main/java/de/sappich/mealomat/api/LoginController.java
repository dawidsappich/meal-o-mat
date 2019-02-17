package de.sappich.mealomat.api;

import de.sappich.mealomat.utils.ApplicationCode;
import de.sappich.mealomat.utils.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping
public class LoginController {


    @GetMapping("login")
    public ResponseEntity<ApplicationResponse> login(Principal principal) {

        ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
        ApplicationResponse response;
        response = builder.setMessage("User found")
                .setIsSuccess(true)
                .setCode(ApplicationCode.OK)
                .setTime(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

}
