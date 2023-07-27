package at.gradwohl.website.model.dienstplan;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

@Embeddable
public class DienstplanId implements Serializable {
    @Column(name = "D_Datum")
    private Date datum;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "D_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @Column(name = "D_von")
    private Timestamp von;

    @MapsId("mitarbeiter")
    @ManyToOne
    @JoinColumn(name = "D_Mitarbeiter", referencedColumnName = "M_ID")
    private Mitarbeiter mitarbeiter;

    // Getters and setters
}
