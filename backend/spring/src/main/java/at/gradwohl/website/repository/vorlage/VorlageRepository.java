package at.gradwohl.website.repository.vorlage;

import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VorlageRepository extends JpaRepository<Vorlage, VorlageId> {
}
