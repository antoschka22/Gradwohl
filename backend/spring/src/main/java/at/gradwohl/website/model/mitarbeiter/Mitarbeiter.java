package at.gradwohl.website.model.mitarbeiter;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Mitarbeiter")
public class Mitarbeiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_ID")
    private int id;

    @Column(name = "M_Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "M_Role", referencedColumnName = "MR_Role")
    private MitarbeiterRole role;

    @ManyToOne
    @JoinColumn(name = "M_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;
}

