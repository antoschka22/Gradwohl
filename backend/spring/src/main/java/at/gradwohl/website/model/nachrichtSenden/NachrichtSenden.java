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
public class NachrichtSenden implements Comparable<NachrichtSenden>{
    @EmbeddedId
    private NachrichtSendenId id;

    @Override
    public int compareTo(NachrichtSenden other) {
        return Long.compare(this.id.getNachricht().getId(), other.id.getNachricht().getId());
    }

}


