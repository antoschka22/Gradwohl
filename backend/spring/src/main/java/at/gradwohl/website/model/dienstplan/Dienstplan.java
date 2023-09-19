package at.gradwohl.website.model.dienstplan;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Dienstplan")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dienstplan {

    @EmbeddedId
    private DienstplanId id;

    @Column(name = "D_bis")
    private LocalTime bis;

    @Column(name="D_Anfrage")
    private boolean anfrage;

}
