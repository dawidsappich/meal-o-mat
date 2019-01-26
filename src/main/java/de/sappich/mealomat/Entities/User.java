package de.sappich.mealomat.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @NonNull
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;
    @NonNull
    private String name;
    @NonNull
    private boolean isActive;
    @NonNull
    private Timestamp createdAt;
    @OneToMany(mappedBy = "user")
    private List<UserComment> comments;

    /**
     * Utility method to create association between user and comment
     * @param comment
     */
    public void addUserComment(UserComment comment) {
        this.comments.add(comment);
        comment.setUser(this);
    }
}
