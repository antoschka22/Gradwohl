package at.gradwohl.website.repository;

import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Integer> {
}
