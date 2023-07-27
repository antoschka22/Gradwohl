package at.gradwohl.website.model.mitarbeiter;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Mitarbeiter")
public class Mitarbeiter {
    @Id
    @Column(name = "M_ID")
    private int id;

    @Column(name = "M_Name")
    private String name;

    @MapsId("mitarbeiterRole")
    @ManyToOne
    @JoinColumn(name = "M_Role", referencedColumnName = "MR_Role")
    private MitarbeiterRole role;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "M_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;
}

