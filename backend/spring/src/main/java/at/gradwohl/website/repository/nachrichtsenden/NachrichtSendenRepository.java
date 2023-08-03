package at.gradwohl.website.repository;

import at.gradwohl.website.model.nachrichtSenden.NachrichtSenden;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSendenId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NachrichtSendenRepository extends JpaRepository<NachrichtSenden, NachrichtSendenId> {
}
