package at.gradwohl.website.repository;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.dienstplan.DienstplanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DienstplanRepository extends JpaRepository<Dienstplan, DienstplanId> {
}
