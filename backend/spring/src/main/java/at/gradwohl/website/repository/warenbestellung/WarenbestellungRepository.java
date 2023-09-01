package at.gradwohl.website.repository.warenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface WarenbestellungRepository extends JpaRepository<Warenbestellung, WarenbestellungId> {
    List<Warenbestellung> findAllById_Datum(LocalDate datum);
    void deleteById(WarenbestellungId id);
    List<Warenbestellung> findAllById_Filiale(Filiale filiale);

    @Modifying
    @Transactional
    @Query("DELETE FROM Warenbestellung w WHERE w.id.filiale = :filiale")
    void deleteByFiliale(Filiale filiale);


}
