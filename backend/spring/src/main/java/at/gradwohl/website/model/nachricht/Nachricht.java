package at.gradwohl.website.model.nachricht;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
