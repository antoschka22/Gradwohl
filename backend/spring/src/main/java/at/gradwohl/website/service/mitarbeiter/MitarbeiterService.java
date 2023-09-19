package at.gradwohl.website.service;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;

    public Mitarbeiter getMitarbeiterById(int id) {
        Optional<Mitarbeiter> existingMitarbeiter = mitarbeiterRepository.findById(id);
        if(!existingMitarbeiter.isPresent())
            throw new IllegalArgumentException("Mitarbeiter existiert nicht");

        return existingMitarbeiter.get();
    }
}
