package at.gradwohl.website.model.vorlage;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Vorlage")
public class Vorlage {
    @EmbeddedId
    private VorlageId id;

    @Column(name = "V_Menge")
    private double menge;

    // Getters and setters
}

