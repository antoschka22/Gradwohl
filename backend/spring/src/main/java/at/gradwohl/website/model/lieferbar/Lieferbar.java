package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;

@Entity
@Table(name = "Lieferbar")
public class Lieferbar {
    @EmbeddedId
    private LieferbarId id;

    @Column(name = "LF_Von")
    private String von;

    @Column(name = "LF_Bis")
    private String bis;

    // Getters and setters
}
