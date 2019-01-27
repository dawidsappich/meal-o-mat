package de.sappich.mealomat.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class MealChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;
    @OneToOne
    @JoinColumn(name = "storeId")
    private StoreLocation storeLocation;
    private String name;
    private String displayName;
}
