package at.gradwohl.website.repository.lieferbar;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LieferbarRepository extends JpaRepository<Lieferbar, LieferbarId> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Lieferbar l WHERE l.id.firma = :firma")
    void deleteByFirma(@Param("firma") Firma firma);

    List<Lieferbar> findAllById_Firma(Firma firma);
}
