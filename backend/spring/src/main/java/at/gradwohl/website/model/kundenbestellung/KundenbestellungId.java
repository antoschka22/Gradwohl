package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KundenbestellungId implements Serializable {
    @Column(name = "KB_Datum")
    private LocalDate datum;

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

}
