package de.sappich.mealomat.Services;

import de.sappich.mealomat.Entities.Authority;
import de.sappich.mealomat.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private Set<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.user = user;
        this.authorities = user.getAuthorities()
                .stream()
                .map(au -> new SimpleGrantedAuthority(au.getAuthority()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getIsActive();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getIsActive();
    }
}
