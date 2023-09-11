package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}

