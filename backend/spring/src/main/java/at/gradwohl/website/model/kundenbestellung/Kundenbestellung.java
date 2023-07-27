package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Kundenbestellung")
public class Kundenbestellung {
    @EmbeddedId
    private KundenbestellungId id;

    @Column(name = "KB_Menge")
    private BigDecimal menge;

    // Getters and setters
}
