package at.gradwohl.website.model.mitarbeiterrole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MitarbeiterRole")
public class MitarbeiterRole {
    @Id
    @Column(name = "MR_Role")
    private String role;

    // Getters and setters
}

