package at.gradwohl.website.repository.vorlage;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VorlageRepository extends JpaRepository<Vorlage, VorlageId> {
    List<Vorlage> findAllById_Filiale(Filiale filiale);
    void deleteById(WarenbestellungId id);

    List<Vorlage> findAllById_Id(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vorlage v WHERE v.id.produkt = :produkt")
    void deleteByProdukt(@Param("produkt") Produkt produkt);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vorlage v WHERE v.id.filiale = :filiale")
    void deleteByFiliale(@Param("filiale") Filiale filiale);
}
