package at.gradwohl.website.repository.mitarbeiter;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Mitarbeiter m SET m.filiale = null WHERE m.filiale = :filiale")
    void updateFilialeToNull(Filiale filiale);

    @Modifying
    @Transactional
    @Query("UPDATE Mitarbeiter m SET m.role = null WHERE m.role = :role")
    void updateRoleToNull(MitarbeiterRole role);
}
