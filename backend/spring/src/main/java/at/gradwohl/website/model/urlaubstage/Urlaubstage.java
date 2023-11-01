package at.gradwohl.website.model.urlaubstage;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Urlaubstage")
public class Urlaubstage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_ID")
    private int id;

    @Column(name="U_Name")
    private String name;

    @Column(name = "U_Datum")
    private LocalDate datum;
}
