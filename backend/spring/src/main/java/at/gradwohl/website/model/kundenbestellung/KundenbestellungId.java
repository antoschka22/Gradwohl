package at.gradwohl.website.model.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KundenbestellungId implements Serializable {
    @Column(name = "KB_Datum")
    private LocalDate datum;

    @MapsId("produkt")
    @ManyToOne
    @JoinColumn(name = "KB_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @Column(name = "KB_Kunde")
    private String kunde;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "KB_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @JsonGetter("datum")
    public LocalDate getDatum() {
        return datum;
    }

    @JsonGetter("produkt")
    public Produkt getProdukt() {
        return produkt;
    }

    @JsonGetter("kunde")
    public String getKunde() {
        return kunde;
    }

    @JsonGetter("filiale")
    public Filiale getFiliale() {
        return filiale;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setKunde(String kunde) {
        this.kunde = kunde;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KundenbestellungId that = (KundenbestellungId) o;

        if (!Objects.equals(datum, that.datum)) return false;
        if (!Objects.equals(produkt, that.produkt)) return false;
        if (!Objects.equals(kunde, that.kunde)) return false;
        return Objects.equals(filiale, that.filiale);
    }

    @Override
    public int hashCode() {
        int result = datum != null ? datum.hashCode() : 0;
        result = 31 * result + (produkt != null ? produkt.hashCode() : 0);
        result = 31 * result + (kunde != null ? kunde.hashCode() : 0);
        result = 31 * result + (filiale != null ? filiale.hashCode() : 0);
        return result;
    }
}
