package at.gradwohl.website.service.kundenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.repository.kundenbestellung.KundenbestellungRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class KundenbestellungService {

    private final KundenbestellungRepository kundenbestellungRepository;

    @Autowired
    public KundenbestellungService (KundenbestellungRepository kundenbestellungRepository){
        this.kundenbestellungRepository = kundenbestellungRepository;
    }

    public List<Kundenbestellung> getAllKundenbestellung() {
        return kundenbestellungRepository.findAll();
    }

    public List<Kundenbestellung> getKundenbestellungByDate(LocalDate datum) {
        return kundenbestellungRepository.findAllById_Datum(datum);
    }

    @Transactional
    public List<Kundenbestellung> addKundenbestellung(List<Kundenbestellung> kundenbestellung) {
        List<Kundenbestellung> bestellungenVorhanden = getAllKundenbestellung();
        for (Kundenbestellung k : kundenbestellung) {
            if (bestellungenVorhanden.contains(k))
                throw new IllegalArgumentException("Already exists");

        }
        return kundenbestellungRepository.saveAll(kundenbestellung);
    }

    @Transactional
    public Kundenbestellung updateKundenbestellung(KundenbestellungId id, Kundenbestellung updatedkundenbestellung) {
        Optional<Kundenbestellung> existingProdukt = kundenbestellungRepository.findById(id);
        if(existingProdukt.isPresent()){
            Kundenbestellung wareToUpdate =
                    Kundenbestellung.builder()
                            .menge(updatedkundenbestellung.getMenge())
                            .id(updatedkundenbestellung.getId())
                            .build();
            kundenbestellungRepository.deleteById(id);
            return kundenbestellungRepository.save(wareToUpdate);
        } else {
            throw new IllegalArgumentException("kundenbestellung not found");
        }
    }

    @Transactional
    public void deleteKundenbestellung(KundenbestellungId id) {
        Optional<Kundenbestellung> existingProdukt = kundenbestellungRepository.findById(id);
        if(existingProdukt.isPresent())
            kundenbestellungRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Warenbestellung doesnt exist");
    }


    public List<Kundenbestellung> getKundenbestellungByFiliale(Filiale filiale) {
        return kundenbestellungRepository.findAllById_Filiale(filiale);
    }


}
