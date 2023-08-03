package at.gradwohl.website.repository;

import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarenbestellungRepository extends JpaRepository<Warenbestellung, WarenbestellungId> {
}
