package at.gradwohl.website.repository.nachrichtsenden;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSenden;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSendenId;
import at.gradwohl.website.service.nachricht.NachrichtService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NachrichtSendenRepository extends JpaRepository<NachrichtSenden, NachrichtSendenId> {

    @Modifying
    @Transactional
    @Query("delete NachrichtSenden n WHERE n.id.nachricht = :nachricht")
    void deleteNachrichtFromInput(Nachricht nachricht);

    @Modifying
    @Transactional
    @Query("DELETE FROM NachrichtSenden n WHERE n.id.filiale = :filiale")
    void deleteByFiliale(Filiale filiale);

    List<NachrichtSenden> getNachrichtSendenByIdFiliale(Filiale filiale);

    List<NachrichtSenden> getNachrichtSendenByIdNachricht(Nachricht nachricht);
}
