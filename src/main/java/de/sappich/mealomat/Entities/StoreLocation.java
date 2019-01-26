package de.sappich.mealomat.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class StoreLocation {
    @Id
    @NonNull
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    private String storeId;
    private Double latitude;
    private Double longitude;
    @NonNull
    private String city;
    @NonNull
    private String street;
    private String houseNr;
}
