package at.gradwohl.website.model.lieferbar;

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

