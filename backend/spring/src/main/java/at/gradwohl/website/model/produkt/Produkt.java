package at.gradwohl.website.model.produkt;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Produkt")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
}
