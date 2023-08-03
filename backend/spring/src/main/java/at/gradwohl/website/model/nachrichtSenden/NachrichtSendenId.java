package at.gradwohl.website.model.nachrichtSenden;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.nachricht.Nachricht;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NachrichtSendenId implements Serializable {

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "NS_Fililale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @MapsId("nachricht")
    @ManyToOne
    @JoinColumn(name = "NS_Nachricht", referencedColumnName = "N_ID")
    private Nachricht nachricht;

    // Getters and setters
}

