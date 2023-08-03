package at.gradwohl.website.repository.produktgruppe;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktgruppeRepository extends JpaRepository<Produktgruppe, String> {
}
