package at.gradwohl.website.repository;

import at.gradwohl.website.model.nachricht.Nachricht;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NachrichtRepository extends JpaRepository<Nachricht, Integer> {
}
