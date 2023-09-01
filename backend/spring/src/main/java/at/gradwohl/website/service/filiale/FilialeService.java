package at.gradwohl.website.service.filiale;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.firma.FirmaRepository;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class FilialeService {

    private final FilialeRepository filialeRepository;
    private final MitarbeiterRepository mitarbeiterRepository;
    private final WarenbestellungRepository warenbestellungRepository;
    private final FirmaRepository firmaRepository;
    @Autowired
    public FilialeService(FilialeRepository filialeRepository,
                          MitarbeiterRepository mitarbeiterRepository,
                          WarenbestellungRepository warenbestellungRepository,
                          FirmaRepository firmaRepository) {
        this.filialeRepository = filialeRepository;
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.warenbestellungRepository = warenbestellungRepository;
        this.firmaRepository = firmaRepository;
    }

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
            warenbestellungRepository.deleteByFiliale(filiale);
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
    "id": 14,
    "filialeName": "Hietzing",
    "filialleiterId": 0,
    "firma": {
    "firmName": "Burgenland"
  }
}
 */