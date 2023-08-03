package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Kundenbestellung")
public class Kundenbestellung {
    @EmbeddedId
    private KundenbestellungId id;

    @Column(name = "KB_Menge")
    private double menge;

    // Getters and setters
}
