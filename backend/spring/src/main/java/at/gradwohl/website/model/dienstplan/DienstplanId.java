package at.gradwohl.website.model.dienstplan;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DienstplanId implements Serializable {
    @Column(name = "D_Datum")
    private LocalDate datum;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "D_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @Column(name = "D_von")
    private LocalTime von;

    @MapsId("mitarbeiter")
    @ManyToOne
    @JoinColumn(name = "D_Mitarbeiter", referencedColumnName = "M_ID")
    private Mitarbeiter mitarbeiter;

    // Getters and setters
}
