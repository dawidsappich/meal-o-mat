package de.sappich.mealomat.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private Boolean isActive;
    @NonNull
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserComment> comments;
    
}
