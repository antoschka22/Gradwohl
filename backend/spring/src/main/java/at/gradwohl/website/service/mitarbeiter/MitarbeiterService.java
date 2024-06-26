package at.gradwohl.website.service.mitarbeiter;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.repository.dienstplan.DienstplanRepository;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;
    private final DienstplanRepository dienstplanRepository;
    private final PasswordEncoder passwordEncoder;

    public Mitarbeiter getMitarbeiterById(int id) {
        Optional<Mitarbeiter> existingMitarbeiter = mitarbeiterRepository.findById(id);
        if(!existingMitarbeiter.isPresent())
            throw new IllegalArgumentException("Mitarbeiter existiert nicht");

        return existingMitarbeiter.get();
    }

    public Mitarbeiter getMitarbeiterByName(String name) {
        Optional<Mitarbeiter> existingMitarbeiter = mitarbeiterRepository.findByName(name);
        if(!existingMitarbeiter.isPresent())
            throw new IllegalArgumentException("Mitarbeiter existiert nicht");

        return existingMitarbeiter.get();
    }

    public List<Mitarbeiter> getAllMitarbeiter(){
        return mitarbeiterRepository.findAll();
    }

    public List<Mitarbeiter> getAllMitarbeiterOfFilialeWithSpringer(Filiale filiale){
        List<Mitarbeiter> mitarbeiters = mitarbeiterRepository.findByFiliale(filiale);
        mitarbeiters.addAll(mitarbeiterRepository.findBySpringer(true));
        return mitarbeiters;
    }

    public Mitarbeiter addMitarbeiter(Mitarbeiter mitarbeiter) {
        Optional<Mitarbeiter> test = mitarbeiterRepository.findById(mitarbeiter.getId());

        if(test.isPresent())
            throw new IllegalArgumentException("Mitarbeiter already exists");

        var mitarbeiterHash = Mitarbeiter.builder()
                .name(mitarbeiter.getName())
                .password(passwordEncoder.encode(mitarbeiter.getPassword()))
                .role(mitarbeiter.getRole())
                .filiale(mitarbeiter.getFiliale())
                .wochenstunden(mitarbeiter.getWochenstunden())
                .build();

        return mitarbeiterRepository.save(mitarbeiterHash);
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
            mitarbeiterRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("Mitarbeiter doesn't exist");

    }
}
