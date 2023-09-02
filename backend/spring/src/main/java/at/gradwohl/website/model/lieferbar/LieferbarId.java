package at.gradwohl.website.model.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.produkt.Produkt;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LieferbarId implements Serializable {
    @MapsId("Produkt")
    @ManyToOne
    @JoinColumn(name = "LF_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("Firma")
    @ManyToOne
    @JoinColumn(name = "LF_Firma", referencedColumnName = "F_Name")
    private Firma firma;

    @JsonGetter("produkt")
    public Produkt getProdukt() {
        return produkt;
    }

    @JsonGetter("firma")
    public Firma getFirma() {
        return firma;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }
}

