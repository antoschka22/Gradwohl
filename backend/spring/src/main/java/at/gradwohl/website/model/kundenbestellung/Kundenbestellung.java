package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Kundenbestellung")
public class Kundenbestellung {
    @EmbeddedId
    private KundenbestellungId id;

    @Column(name = "KB_Menge")
    private double menge;

    @JsonGetter("id")
    public KundenbestellungId getId() {
        return id;
    }

    @JsonGetter("menge")
    public double getMenge() {
        return menge;
    }

    public void setId(KundenbestellungId id) {
        this.id = id;
    }

    public void setMenge(double menge) {
        this.menge = menge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kundenbestellung that = (Kundenbestellung) o;

        return Objects.equals(id, that.id) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
