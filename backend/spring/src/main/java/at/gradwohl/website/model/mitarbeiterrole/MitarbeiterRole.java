package at.gradwohl.website.model.mitarbeiterrole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "MitarbeiterRole")
public class MitarbeiterRole {
    @Id
    @Column(name = "MR_Role")
    private String role;
}

