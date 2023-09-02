package at.gradwohl.website.model.mitarbeiterrole;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MitarbeiterRole")
public class MitarbeiterRole {
    @Id
    @Column(name = "MR_Role")
    private String role;

    @JsonGetter("role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

