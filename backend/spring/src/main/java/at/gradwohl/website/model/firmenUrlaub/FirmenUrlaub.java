package at.gradwohl.website.model.firmenUrlaub;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "firmenUrlaub")
public class FirmenUrlaub {

    @EmbeddedId
    private FirmenUrlaubId id;
}
