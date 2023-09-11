package at.gradwohl.website.model.filiale;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Filiale")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Filiale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private int id;

    @Column(name = "F_Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "F_Filialleiter", referencedColumnName = "M_ID")
    private Mitarbeiter filialleiter;

    @ManyToOne
    @JoinColumn(name = "F_Firma", referencedColumnName = "F_Name")
    private Firma firma;
}


