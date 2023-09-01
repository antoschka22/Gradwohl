package at.gradwohl.website.model.warenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarenbestellungId implements Serializable {
    @Column(name = "WB_Datum")
    private LocalDate datum;

    @MapsId("produkt")
    @ManyToOne
    @JoinColumn(name = "WB_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "WB_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @JsonGetter("datum")
    public LocalDate getDatum() {
        return datum;
    }

    @JsonGetter("produkt")
    public Produkt getProdukt() {
        return produkt;
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

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }
}

