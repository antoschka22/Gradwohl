package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.firma.Firma;
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
public class LieferbarId implements Serializable {
    @MapsId("Produkt")
    @ManyToOne
    @JoinColumn(name = "LF_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("Firma")
    @ManyToOne
    @JoinColumn(name = "LF_Firma", referencedColumnName = "F_Name")
    private Firma firma;
}

