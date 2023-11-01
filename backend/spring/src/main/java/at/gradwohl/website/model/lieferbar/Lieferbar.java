package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Lieferbar")
public class Lieferbar {
    @EmbeddedId
    private LieferbarId id;

    @Column(name = "LF_Montag")
    private boolean montag;

    @Column(name = "LF_Dienstag")
    private boolean dienstag;

    @Column(name = "LF_Mittwoch")
    private boolean mittwoch;

    @Column(name = "LF_Donnerstag")
    private boolean donnerstag;

    @Column(name = "LF_Freitag")
    private boolean freitag;

    @Column(name = "LF_Samstag")
    private boolean samstag;

    @Column(name = "LF_Sonntag")
    private boolean sonntag;
}

