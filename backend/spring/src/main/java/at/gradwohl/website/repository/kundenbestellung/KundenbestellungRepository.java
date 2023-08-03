package at.gradwohl.website.repository.kundenbestellung;

import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundenbestellungRepository extends JpaRepository<Kundenbestellung, KundenbestellungId> {
}
