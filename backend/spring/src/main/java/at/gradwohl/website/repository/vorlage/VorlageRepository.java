package at.gradwohl.website.repository.vorlage;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VorlageRepository extends JpaRepository<Vorlage, VorlageId> {
    List<Vorlage> findAllById_Filiale(Filiale filiale);
    void deleteById(WarenbestellungId id);

    List<Vorlage> findAllById_Id(int id);
}
