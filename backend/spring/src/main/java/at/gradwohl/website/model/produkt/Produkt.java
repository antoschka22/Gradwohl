package at.gradwohl.website.model.produkt;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.persistence.*;

@Entity
@Table(name = "Produkt")
public class Produkt {
    @Id
    @Column(name = "P_ID")
    private int id;

    @Column(name = "P_Name")
    private int name;

    @MapsId("produktgruppe")
    @ManyToOne
    @JoinColumn(name = "P_Gruppe", referencedColumnName = "PG_Name")
    private Produktgruppe produktgruppe;

    @Column(name = "P_Bio")
    private boolean bio;

    @Column(name = "P_Mehl")
    private String mehl;

    @Column(name = "P_HB")
    private boolean hb;

    // Getters and setters
}

