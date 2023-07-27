package at.gradwohl.website.model.firma;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Firma")
public class Firma {

    @Id
    @Column(name = "F_Name")
    private String name;

    // Getters and setters
}
