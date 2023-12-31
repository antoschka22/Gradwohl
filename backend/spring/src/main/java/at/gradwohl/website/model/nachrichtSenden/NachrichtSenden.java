package at.gradwohl.website.model.nachrichtSenden;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "NS_gelesen")
    private boolean gelesen;
    @Override
    public int compareTo(NachrichtSenden other) {
        return Long.compare(this.id.getNachricht().getId(), other.id.getNachricht().getId());
    }

}


