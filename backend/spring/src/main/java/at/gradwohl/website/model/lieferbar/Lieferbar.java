package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lieferbar")
public class Lieferbar {
    @EmbeddedId
    private LieferbarId id;

    @Column(name = "LF_Von")
    @Convert(converter = WochentagConverter.class)
    private Wochentag von;

    @Column(name = "LF_Bis")
    @Convert(converter = WochentagConverter.class)
    private Wochentag bis;

    @JsonGetter("id")
    public LieferbarId getId() {
        return id;
    }

    @JsonGetter("von")
    public Wochentag getVon() {
        return von;
    }

    @JsonGetter("bis")
    public Wochentag getBis() {
        return bis;
    }

    public void setId(LieferbarId id) {
        this.id = id;
    }

    public void setVon(Wochentag von) {
        this.von = von;
    }

    public void setBis(Wochentag bis) {
        this.bis = bis;
    }
}

