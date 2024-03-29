package at.gradwohl.website.model.vorlage;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VorlageId implements Serializable {
    @Column(name = "V_Name")
    private String name;

    @MapsId("produkt")
    @ManyToOne
    @JoinColumn(name = "V_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "V_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;
}

