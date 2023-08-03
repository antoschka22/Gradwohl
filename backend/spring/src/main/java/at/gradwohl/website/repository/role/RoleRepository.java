package at.gradwohl.website.repository;

import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<MitarbeiterRole, String> {
}
