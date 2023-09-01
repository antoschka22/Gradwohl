package at.gradwohl.website.model.warenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Column;
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
@Table(name = "Warenbestellung")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Warenbestellung {
    @EmbeddedId
    private WarenbestellungId id;

    @Column(name = "WB_Menge")
    private double menge;

    @JsonGetter("id")
    public WarenbestellungId getId() {
        return id;
    }

    @JsonGetter("menge")
    public double getMenge() {
        return menge;
    }

    public void setId(WarenbestellungId id) {
        this.id = id;
    }

    public void setMenge(double menge) {
        this.menge = menge;
    }
}
