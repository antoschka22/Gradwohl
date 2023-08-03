package at.gradwohl.website.model.nachrichtSenden;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NachrichtSenden")
public class NachrichtSenden {
    @EmbeddedId
    private NachrichtSendenId id;

    // Getters and setters
}


