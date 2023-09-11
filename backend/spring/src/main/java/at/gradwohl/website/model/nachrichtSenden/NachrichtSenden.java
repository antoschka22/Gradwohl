package at.gradwohl.website.model.nachrichtSenden;

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
@Table(name = "NachrichtSenden")
public class NachrichtSenden {
    @EmbeddedId
    private NachrichtSendenId id;

}


