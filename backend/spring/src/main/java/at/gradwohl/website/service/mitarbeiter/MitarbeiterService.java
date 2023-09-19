package at.gradwohl.website.service.mitarbeiter;

import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.repository.dienstplan.DienstplanRepository;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;
    private final DienstplanRepository dienstplanRepository;
    private final FilialeRepository filialeRepository;

    public Mitarbeiter getMitarbeiterById(int id) {
        Optional<Mitarbeiter> existingMitarbeiter = mitarbeiterRepository.findById(id);
        if(!existingMitarbeiter.isPresent())
            throw new IllegalArgumentException("Mitarbeiter existiert nicht");

        return existingMitarbeiter.get();
    }

    public List<Mitarbeiter> getAllMitarbeiter(){
        return mitarbeiterRepository.findAll();
    }

    public Mitarbeiter addMitarbeiter(Mitarbeiter mitarbeiter) {
        Optional<Mitarbeiter> test = mitarbeiterRepository.findById(mitarbeiter.getId());

        if(test.isPresent())
            throw new IllegalArgumentException("Mitarbeiter already exists");

        return mitarbeiterRepository.save(mitarbeiter);
    }


    public Mitarbeiter updateMitarbeiter(int id, Mitarbeiter updatedMitarbeiter) {
        Optional<Mitarbeiter> optionalMitarbeiter = mitarbeiterRepository.findById(id);
        if(optionalMitarbeiter.isPresent()){
            updatedMitarbeiter.setId(id);
            return mitarbeiterRepository.save(updatedMitarbeiter);
        } else
            throw new IllegalArgumentException("Empty Body");
    }

    public void deleteMitarbeiter(int id) {
        Optional<Mitarbeiter> optionalMitarbeiter = mitarbeiterRepository.findById(id);

        if (optionalMitarbeiter.isPresent()) {
            Mitarbeiter mitarbeiter = optionalMitarbeiter.get();
            dienstplanRepository.deleteMitarbeiterIfTrue(mitarbeiter);
            filialeRepository.setFilialleiterToNull(mitarbeiter);
            mitarbeiterRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("Mitarbeiter doesn't exist");

    }
}
