package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lieferbar")
public class Lieferbar {
    @EmbeddedId
    private LieferbarId id;

    @Column(name = "LF_Von")
    @Convert(converter = WochentagConverter.class)
    private Wochentag von;

    @Column(name = "LF_Bis")
    @Convert(converter = WochentagConverter.class)
    private Wochentag bis;

    // Getters and setters
}

