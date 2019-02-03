package de.sappich.mealomat.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authorities")
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private Boolean isActive;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserComment> comments;


    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities = new ArrayList<>();




    public User(String email, String password, Set<Authority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities.addAll(authorities);
    }

    public User(String email, String password, Authority ... authorities) {
        this(email, password, new HashSet<>(Arrays.asList(authorities)));
    }

}
