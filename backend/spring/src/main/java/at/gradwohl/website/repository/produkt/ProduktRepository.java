package at.gradwohl.website.repository.produkt;

import at.gradwohl.website.model.produkt.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduktRepository extends JpaRepository<Produkt, Integer> {

    List<Produkt> findByProduktgruppe_Name(String produktgruppeName);

    @Query("SELECT p FROM Produkt p WHERE p.produktgruppe.name = :gruppe AND p.hb = :hb")
    List<Produkt> findByGruppeAndHb(@Param("gruppe") String gruppe, @Param("hb") boolean hb);

}
