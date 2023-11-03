package at.gradwohl.website.model.nachricht;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Nachricht")
public class Nachricht {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private long id;

    @Column(name = "N_Nachricht", columnDefinition = "TEXT")
    private String nachricht;

    @Column(name = "N_Datum")
    private LocalDate datum;
}
