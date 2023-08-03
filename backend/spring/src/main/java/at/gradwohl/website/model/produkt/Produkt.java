package at.gradwohl.website.model.produkt;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produkt")
public class Produkt {
    @Id
    @Column(name = "P_ID")
    private int id;

    @Column(name = "P_Name")
    private String name;

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

