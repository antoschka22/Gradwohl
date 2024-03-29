package at.gradwohl.website.repository.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface KundenbestellungRepository extends JpaRepository<Kundenbestellung, KundenbestellungId> {

    List<Kundenbestellung> findAllById_Datum(LocalDate datum);
    void deleteById(WarenbestellungId id);
    List<Kundenbestellung> findAllById_Filiale(Filiale filiale);

    @Transactional
    @Modifying
    @Query("DELETE FROM Kundenbestellung k WHERE k.id.produkt = :produkt")
    void deleteByProdukt(@Param("produkt") Produkt produkt);

    @Transactional
    @Modifying
    @Query("DELETE FROM Kundenbestellung k WHERE k.id.filiale = :filiale")
    void deleteByFiliale(@Param("filiale") Filiale filiale);

}
