package at.gradwohl.website.model.kundenbestellung;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Kundenbestellung")
public class Kundenbestellung {
    @EmbeddedId
    private KundenbestellungId id;

    @Column(name = "KB_Menge")
    private double menge;

    @Column(name = "KB_Telefonnummer")
    private String telefonnummer;
}
