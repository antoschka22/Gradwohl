package at.gradwohl.website.model.warenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarenbestellungId implements Serializable {
    @Column(name = "WB_Datum")
    private LocalDate datum;

    @MapsId("produkt")
    @ManyToOne
    @JoinColumn(name = "WB_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "WB_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

}

