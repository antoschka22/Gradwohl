package at.gradwohl.website.repository;

import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LieferbarRepository extends JpaRepository<Lieferbar, LieferbarId> {
}
