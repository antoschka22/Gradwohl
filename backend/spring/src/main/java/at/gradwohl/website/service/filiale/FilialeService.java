package at.gradwohl.website.service.filiale;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.repository.dienstplan.DienstplanRepository;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.kundenbestellung.KundenbestellungRepository;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import at.gradwohl.website.repository.vorlage.VorlageRepository;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilialeService {

    private final FilialeRepository filialeRepository;
    private final MitarbeiterRepository mitarbeiterRepository;
    private final WarenbestellungRepository warenbestellungRepository;
    private final DienstplanRepository dienstplanRepository;
    private final KundenbestellungRepository kundenbestellungRepository;
    private final VorlageRepository vorlageRepository;

    public List<Filiale> getAllFilialen() {
        return filialeRepository.findAll();
    }

    public Filiale addFiliale(Filiale filiale) {
        Optional<Filiale> test = filialeRepository.findById(filiale.getId());

        if(test.isPresent())
            throw new IllegalArgumentException("Filiale already exists");

        return filialeRepository.save(filiale);
    }


    public Filiale updateFiliale(int id, Filiale updatedFiliale) {
        Optional<Filiale> optionalFiliale = filialeRepository.findById(id);
        if(optionalFiliale.isPresent()){
            updatedFiliale.setId(id);
            return filialeRepository.save(updatedFiliale);
        } else
            throw new IllegalArgumentException("Empty Body");
    }

    public void deleteFiliale(int id) {
        Optional<Filiale> optionalFiliale = filialeRepository.findById(id);

        if (optionalFiliale.isPresent()) {
            Filiale filiale = optionalFiliale.get();
            mitarbeiterRepository.updateFilialeToNull(filiale);
            dienstplanRepository.deleteFilialeIfTrue(filiale);
            warenbestellungRepository.deleteByFiliale(filiale);
            kundenbestellungRepository.deleteByFiliale(filiale);
            vorlageRepository.deleteByFiliale(filiale);
            filialeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Filiale doesn't exist");
        }
    }

    public Filiale getFilialeById(int filialeId) {
        Optional<Filiale> optionalFiliale = filialeRepository.findById(filialeId);

        if (optionalFiliale.isPresent()) {
            return optionalFiliale.get();
        } else {
            throw new IllegalArgumentException("Filiale with ID " + filialeId + " not found");
        }
    }
}


/*
{
    "id": 1000,
    "name": "adfdf",
    "filialleiter": {
      "id": 1,
      "name": "Barbara",
      "password": "$2a$10$EYGa1zYzo5MJD5wDmHklfuyH4yd89eHrPHHUozWdKQA43u22uUb2W",
      "role": {
        "role": "Leiter"
      },
      "filiale": {
        "id": 14,
        "name": "Hietzing",
        "firma": {
          "name": "Wien"
        }
      }
    },
    "firma": {
      "name": "Olivers Teigwerkstätte"
    }
  }
 */