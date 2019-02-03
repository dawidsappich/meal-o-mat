package de.sappich.mealomat.Configurations;

import de.sappich.mealomat.Entities.Authority;
import de.sappich.mealomat.Entities.User;
import de.sappich.mealomat.Repositories.AuthorityRepository;
import de.sappich.mealomat.Services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Arrays;

@EnableWebSecurity
@Log4j2
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Ignore security for h2-console
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    /**
     * configure security to use our authentication provider
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Insert fist user
     */
    @Bean
    @Transactional
    public ApplicationRunner applicationRunner() {
        return args -> {
            Authority user = new Authority("ROLE_USER");
            Authority admin = new Authority("ROLE_ADMIN");
//            authorityRepository.save(user);
//            authorityRepository.save(admin);
            User dawid = userService.createUser("dawid", passwordEncoder().encode("password"), user, admin);
            dawid.setAuthorities(Arrays.asList(user, admin));
            log.info(dawid.toString());
        };
    }
}
