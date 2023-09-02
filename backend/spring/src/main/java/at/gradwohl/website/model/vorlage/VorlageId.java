package at.gradwohl.website.model.vorlage;

import at.gradwohl.website.model.filiale.Filiale;
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
public class VorlageId implements Serializable {
    @Column(name = "V_ID")
    private int id;

    @MapsId("produkt")
    @ManyToOne
    @JoinColumn(name = "V_Produkt", referencedColumnName = "P_ID")
    private Produkt produkt;

    @MapsId("filiale")
    @ManyToOne
    @JoinColumn(name = "V_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonGetter("produkt")
    public Produkt getProdukt() {
        return produkt;
    }

    @JsonGetter("filiale")
    public Filiale getFiliale() {
        return filiale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }
}

