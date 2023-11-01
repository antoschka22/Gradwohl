package at.gradwohl.website.repository.firma;

import at.gradwohl.website.model.firma.Firma;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FirmaRepository extends JpaRepository<Firma, String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Firma f WHERE f.name = :name")
    void deleteByFirma(@Param("name") String name);

}
