package at.gradwohl.website.model.produkt;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produkt")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Produkt implements Serializable {
    @Id
    @Column(name = "P_ID")
    private int id;

    @Column(name = "P_Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "P_Gruppe", referencedColumnName = "PG_Name")
    private Produktgruppe produktgruppe;

    @Column(name = "P_Bio")
    private boolean bio;

    @Column(name = "P_Mehl")
    private Mehl mehl;

    @Column(name = "P_HB")
    private boolean hb;

    @Column(name = "P_Mehl_Mischung")
    private Mehl mehlMischung;

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("produktgruppe")
    public Produktgruppe getProduktgruppe() {
        return produktgruppe;
    }

    @JsonGetter("bio")
    public boolean isBio() {
        return bio;
    }

    @JsonGetter("mehl")
    public Mehl getMehl() {
        return mehl;
    }

    @JsonGetter("hb")
    public boolean isHb() {
        return hb;
    }

    @JsonGetter("mehlMischung")
    public Mehl getMehlMischung() {
        return mehlMischung;
    }
}
