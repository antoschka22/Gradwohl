package at.gradwohl.website.repository.produkt;

import at.gradwohl.website.model.produkt.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktRepository extends JpaRepository<Produkt, Integer> {
}
