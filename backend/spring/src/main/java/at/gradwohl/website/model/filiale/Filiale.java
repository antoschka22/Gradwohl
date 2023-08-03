package at.gradwohl.website.model.filiale;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Filiale")
public class Filiale {
    @Id
    @Column(name = "F_ID")
    private int id;

    @Column(name = "F_Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "F_Filialleiter", referencedColumnName = "M_ID")
    private Mitarbeiter filialleiter;

    @ManyToOne
    @JoinColumn(name = "F_Firma", referencedColumnName = "F_Name")
    private Firma firma;

    // Getters and Setters (if not already present)
}


