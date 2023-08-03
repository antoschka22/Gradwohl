package at.gradwohl.website.model.produktgruppe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produktgruppe")
public class Produktgruppe {
    @Id
    @Column(name = "PG_Name")
    private String name;

    // Getters and setters
}

