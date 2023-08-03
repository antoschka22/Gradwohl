package at.gradwohl.website.model.dienstplan;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Dienstplan")
public class Dienstplan {

    @EmbeddedId
    private DienstplanId id;

    @Column(name = "D_bis")
    private LocalTime bis;

}
