package at.gradwohl.website.repository.firma;

import at.gradwohl.website.model.firma.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmaRepository extends JpaRepository<Firma, String> {
}
