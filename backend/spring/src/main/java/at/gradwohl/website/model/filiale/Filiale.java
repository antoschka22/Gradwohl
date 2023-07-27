package at.gradwohl.website.model.filiale;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.persistence.*;

@Entity
@Table(name = "Filiale")
public class Filiale {
    @Id
    @Column(name = "F_ID")
    private int id;

    @Column(name = "F_Name")
    private String name;

    @MapsId("Mitarbeiter")
    @ManyToOne
    @JoinColumn(name = "F_Filialleiter", referencedColumnName = "M_ID")
    private Mitarbeiter filialleiter;

    @MapsId("Firma")
    @ManyToOne
    @JoinColumn(name = "F_Firma", referencedColumnName = "F_Name")
    private Firma firma;

}

