package de.sappich.mealomat.configurations;

import de.sappich.mealomat.entities.Authority;
import de.sappich.mealomat.entities.User;
import de.sappich.mealomat.services.UserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Arrays;

@EnableWebSecurity
@Log4j2
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ignore security for h2 console
        web.ignoring().antMatchers("/h2-console/**");
        // ignore security for swagger ui
        web.ignoring().antMatchers("/swagger-ui.html**");

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // FIXME: enable CRSF when frontend (angular) is available
        http.csrf().ignoringAntMatchers("/register/**");

        // user HTTP Basic Authentication
        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers("/register/**").permitAll()
                .anyRequest().authenticated();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * insert fist user
     */
    @Bean
    @Transactional
    public ApplicationRunner applicationRunner() {
        return args -> {
            Authority user = new Authority("USER");
            Authority admin = new Authority("ADMIN");
            User dawid = userService.saveUser("dawid", "password", user, admin);
            dawid.setAuthorities(Arrays.asList(user, admin));
            log.info(dawid.toString());

        };
    }
}
