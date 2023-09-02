package at.gradwohl.website.repository.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KundenbestellungRepository extends JpaRepository<Kundenbestellung, KundenbestellungId> {

    List<Kundenbestellung> findAllById_Datum(LocalDate datum);
    void deleteById(WarenbestellungId id);
    List<Kundenbestellung> findAllById_Filiale(Filiale filiale);

}
