package at.gradwohl.website.model.vorlage;

import com.fasterxml.jackson.annotation.JsonGetter;
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

    @JsonGetter("id")
    public VorlageId getId() {
        return id;
    }

    @JsonGetter("menge")
    public double getMenge() {
        return menge;
    }

    public void setId(VorlageId id) {
        this.id = id;
    }

    public void setMenge(double menge) {
        this.menge = menge;
    }
}

