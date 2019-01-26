package de.sappich.mealomat.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserComment {
    @Id
    private String commentId;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @NonNull
    private Timestamp createdAt;
    private String content;
}
