package at.gradwohl.website.model.dienstplan;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "Dienstplan")
public class Dienstplan {

    @EmbeddedId
    private DienstplanId id;

    @Column(name = "D_bis")
    private Timestamp bis;

}
