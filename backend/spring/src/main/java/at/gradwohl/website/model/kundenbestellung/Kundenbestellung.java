package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Kundenbestellung")
public class Kundenbestellung {
    @EmbeddedId
    private KundenbestellungId id;

    @Column(name = "KB_Menge")
    private double menge;

    @Column(name = "KB_Telefonnummer")
    private String telefonnummer;
}
