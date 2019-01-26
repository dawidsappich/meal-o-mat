package de.sappich.mealomat.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class MealChoice {
    @Id
    @NonNull
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    private String mealId;
    @OneToOne
    @JoinColumn(name = "storeId")
    private StoreLocation storeLocation;
    private String name;
    private String displayName;
}
