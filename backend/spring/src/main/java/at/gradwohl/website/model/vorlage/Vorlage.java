package at.gradwohl.website.model.vorlage;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Vorlage")
public class Vorlage {
    @EmbeddedId
    private VorlageId id;

    @Column(name = "V_Menge")
    private double menge;
}

