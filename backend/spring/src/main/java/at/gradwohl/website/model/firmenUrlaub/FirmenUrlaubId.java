package at.gradwohl.website.model.firmenUrlaub;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.urlaubstage.Urlaubstage;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FirmenUrlaubId implements Serializable {

    @MapsId("firma")
    @ManyToOne
    @JoinColumn(name = "FU_Firma", referencedColumnName = "F_Name")
    private Firma firma;

    @MapsId("urlaubstage")
    @ManyToOne
    @JoinColumn(name = "FU_Urlaubstage", referencedColumnName = "U_ID")
    private Urlaubstage urlaubstage;
}
