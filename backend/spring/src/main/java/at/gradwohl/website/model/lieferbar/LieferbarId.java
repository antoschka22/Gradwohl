package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class LieferbarId implements Serializable {
    @MapsId("Produkt")
    @ManyToOne
    @JoinColumn(name = "LF_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("Firma")
    @ManyToOne
    @JoinColumn(name = "LF_Firma", referencedColumnName = "F_Name")
    private Firma firma;

    // Getters and setters
}

