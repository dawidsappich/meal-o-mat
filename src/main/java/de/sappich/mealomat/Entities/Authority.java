package de.sappich.mealomat.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@ToString(exclude = "users")
public class Authority {

    @Id @GeneratedValue
    private Long id;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users = new ArrayList<>();


    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(String authority, Set<User> users) {
        this.authority = authority;
        this.users.addAll(users);
    }
}
