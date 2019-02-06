package de.sappich.mealomat.api;

import de.sappich.mealomat.utils.ApplicationCode;
import de.sappich.mealomat.utils.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("health-check")
public class HealthCheckController {

    @GetMapping("ping")
    public ResponseEntity<ApplicationResponse> check() {
        ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
        builder.setCode(ApplicationCode.OK);
        builder.setIsSuccess(true);
        builder.setMessage("heartbeat");
        builder.setTime(LocalDateTime.now());
        return ResponseEntity.ok(builder.build());
    }

}
