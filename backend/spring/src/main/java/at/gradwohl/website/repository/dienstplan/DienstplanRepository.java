package at.gradwohl.website.repository.dienstplan;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.dienstplan.DienstplanId;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DienstplanRepository extends JpaRepository<Dienstplan, DienstplanId> {

    List<Dienstplan> findByIdFiliale(Filiale filiale);

    @Modifying
    @Transactional
    @Query("DELETE Dienstplan d WHERE d.id.mitarbeiter = :mitarbeiter")
    void deleteMitarbeiterIfTrue(Mitarbeiter mitarbeiter);

    @Modifying
    @Transactional
    @Query("DELETE Dienstplan d WHERE d.id.filiale = :filiale")
    void deleteFilialeIfTrue(Filiale filiale);
}
