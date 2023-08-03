package at.gradwohl.website.repository;

import at.gradwohl.website.model.filiale.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialeRepository extends JpaRepository<Filiale, Integer> {
}
