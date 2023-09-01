package at.gradwohl.website.repository.produktgruppe;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProduktgruppeRepository extends JpaRepository<Produktgruppe, String> {

    List<Produktgruppe> findAll();

    Optional<Produktgruppe> findByName(String produktgruppeName);

    @Modifying
    @Transactional
    @Query("UPDATE Produkt p SET p.produktgruppe = null WHERE p.produktgruppe = :produktgruppe")
    void setProduktgruppeToNullForProdukte(Produktgruppe produktgruppe);
}
