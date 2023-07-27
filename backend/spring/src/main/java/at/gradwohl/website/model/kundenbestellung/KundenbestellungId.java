package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public class KundenbestellungId implements Serializable {
    @Column(name = "KB_Datum")
    private Date datum;

    @MapsId("produkt")
    @ManyToOne
    @JoinColumn(name = "KB_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @Column(name = "KB_Kunde")
    private String kunde;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "KB_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    // Getters and setters
}
