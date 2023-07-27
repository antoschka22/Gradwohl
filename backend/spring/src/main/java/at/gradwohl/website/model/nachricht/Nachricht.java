package at.gradwohl.website.model.nachricht;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Nachricht")
public class Nachricht {
    @Id
    @Column(name = "N_ID")
    private int id;

    @Column(name = "N_Nachricht", length = 1073741824)
    private String nachricht;

    // Getters and setters
}
