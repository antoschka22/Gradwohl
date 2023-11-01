package at.gradwohl.website.repository.firmenUrlaub;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaub;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaubId;
import at.gradwohl.website.model.urlaubstage.Urlaubstage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FirmenUrlaubRepository extends JpaRepository<FirmenUrlaub, FirmenUrlaubId> {

    @Transactional
    @Modifying
    @Query("DELETE FROM FirmenUrlaub u WHERE u.id.urlaubstage = :id")
    void deleteByUrlaubstag(@Param("id") Urlaubstage id);

    @Transactional
    @Modifying
    @Query("DELETE FROM FirmenUrlaub u WHERE u.id.firma = :id")
    void deleteByFirma(@Param("id") Firma id);

    List<FirmenUrlaub> findByIdFirma(Firma firma);

}
