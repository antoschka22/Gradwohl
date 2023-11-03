package at.gradwohl.website.repository.mitarbeiter;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Mitarbeiter m SET m.filiale = null WHERE m.filiale = :filiale")
    void updateFilialeToNull(Filiale filiale);

    @Modifying
    @Transactional
    @Query("UPDATE Mitarbeiter m SET m.role = null WHERE m.role = :role")
    void updateRoleToNull(MitarbeiterRole role);

    Optional<Mitarbeiter> findByName(String name);

    List<Mitarbeiter> findByFiliale(Filiale filiale);

    List<Mitarbeiter> findBySpringer(boolean b);
}
