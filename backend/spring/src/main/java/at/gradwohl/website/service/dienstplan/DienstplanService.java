package at.gradwohl.website.service.dienstplan;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.dienstplan.DienstplanId;
import at.gradwohl.website.repository.dienstplan.DienstplanRepository;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DienstplanService {

    private final DienstplanRepository dienstplanRepository;
    private final FilialeRepository filialeRepository;

    public List<Dienstplan> getDienstplanByFiliale(Filiale filiale) {
        Optional<Filiale> existingFiliale = filialeRepository.findById(filiale.getId());
        if(!existingFiliale.isPresent())
            throw new IllegalArgumentException("Filiale existiert nicht");

        return dienstplanRepository.findByIdFiliale(filiale);
    }

    public Dienstplan saveDienstplan(Dienstplan dienstplane) {
        Optional<Dienstplan> dienstplansVorhanden = dienstplanRepository.findById(dienstplane.getId());
        if(dienstplansVorhanden.isPresent())
            throw new IllegalArgumentException("Already exists");

        return dienstplanRepository.save(dienstplane);
    }

    @Transactional
    public Dienstplan updateDienstplan(DienstplanId id, Dienstplan updatedDienstplan) {
        Optional<Dienstplan> existingProdukt = dienstplanRepository.findById(id);
        if(existingProdukt.isPresent()){
            Dienstplan wareToUpdate =
                    Dienstplan.builder()
                            .bis(updatedDienstplan.getBis())
                            .urlaub(updatedDienstplan.isUrlaub())
                            .id(updatedDienstplan.getId())
                            .build();
            dienstplanRepository.deleteById(id);
            return dienstplanRepository.save(wareToUpdate);
        } else {
            throw new IllegalArgumentException("Dienstplan not found");
        }
    }

    @Transactional
    public void deleteDienstplan(DienstplanId id) {
        Optional<Dienstplan> existingDienstplan = dienstplanRepository.findById(id);
        if(existingDienstplan.isPresent())
            dienstplanRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Dienstplan doesnt exist");
    }
}