package at.gradwohl.website.repository.filiale;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialeRepository extends JpaRepository<Filiale, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Filiale f SET f.firma = null WHERE f.firma = :firmaInput")
    void setFirmaToNull(Firma firmaInput);

    @Modifying
    @Transactional
    @Query("UPDATE Filiale f SET f.filialleiter = null WHERE f.filialleiter = :filialleiterInput")
    void setFilialleiterToNull(Mitarbeiter filialleiterInput);

    void deleteById(int id);
}
