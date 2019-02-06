package de.sappich.mealomat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NonNull
    @JsonIgnore
    private User user;
    @Temporal(TemporalType.DATE)
    private Date dateVoted;
    @ManyToOne
    @NonNull
    private Meal meal;
}
