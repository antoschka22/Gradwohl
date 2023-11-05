package at.gradwohl.website.service.vorlage;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.repository.vorlage.VorlageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VorlageService {

    private final VorlageRepository vorlageRepository;

    public List<Vorlage> getAllVorlagen(){ return vorlageRepository.findAll(); }

    @Transactional
    public List<Vorlage> addVorlage(List<Vorlage> vorlage) { return vorlageRepository.saveAll(vorlage); }

    @Transactional
    public Vorlage updateVorlage(VorlageId id, Vorlage updatedvorlage) {
        Optional<Vorlage> existingVorlage = vorlageRepository.findById(id);
        if(existingVorlage.isPresent()){
            Vorlage vorlageToUpdate =
                    Vorlage.builder()
                            .menge(updatedvorlage.getMenge())
                            .id(updatedvorlage.getId())
                            .build();
            vorlageRepository.deleteById(id);
            return vorlageRepository.save(vorlageToUpdate);
        } else {
            throw new IllegalArgumentException("Vorlage not found");
        }
    }

    @Transactional
    public void deleteVorlage(VorlageId id) {
        Optional<Vorlage> existingProdukt = vorlageRepository.findById(id);
        if(existingProdukt.isPresent())
            vorlageRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Vorlage doesnt exist");
    }

    public List<Vorlage> getVorlageByFiliale(Filiale filiale) {
        return vorlageRepository.findAllById_Filiale(filiale);
    }

    public List<Vorlage> getVorlageById(String name){
        List<Vorlage> optionalVorlage = vorlageRepository.findAllById_Name(name);

        if(optionalVorlage.size() > 0)
            return vorlageRepository.findAllById_Name(name);
        else
            throw new IllegalArgumentException("Vorlage doesnt exist");
    }

}
