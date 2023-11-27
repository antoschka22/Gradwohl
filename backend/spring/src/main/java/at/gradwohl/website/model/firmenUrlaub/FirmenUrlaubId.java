package at.gradwohl.website.model.firmenUrlaub;

import at.gradwohl.website.model.filiale.Filiale;
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

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "FU_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @MapsId("urlaubstage")
    @ManyToOne
    @JoinColumn(name = "FU_Urlaubstage", referencedColumnName = "U_ID")
    private Urlaubstage urlaubstage;
}
