package de.sappich.mealomat.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class UserVoting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userVotingId;
    @ManyToOne
    @NonNull
    private User user;
    private Timestamp votedAt;
    @ManyToOne
    @NonNull
    private MealChoice choice;
}
