package at.gradwohl.website.repository.nachricht;

import at.gradwohl.website.model.nachricht.Nachricht;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NachrichtRepository extends JpaRepository<Nachricht, Long> {
    List<Nachricht> findAllByOrderByDatumDesc();
}
