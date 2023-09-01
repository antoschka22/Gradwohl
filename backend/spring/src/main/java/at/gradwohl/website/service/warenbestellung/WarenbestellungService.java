package at.gradwohl.website.service.warenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WarenbestellungService {
    private final WarenbestellungRepository warenbestellungRepository;

    @Autowired
    public WarenbestellungService(WarenbestellungRepository warenbestellungRepository) {
        this.warenbestellungRepository = warenbestellungRepository;
    }

    public List<Warenbestellung> getAllWarenbestellungen() {
        return warenbestellungRepository.findAll();
    }

    public List<Warenbestellung> getWarenbestellungenByDate(LocalDate datum) {
        return warenbestellungRepository.findAllById_Datum(datum);
    }

    @Transactional
    public List<Warenbestellung> addWarenbestellungen(List<Warenbestellung> warenbestellungen) {
        return warenbestellungRepository.saveAll(warenbestellungen);
    }

    @Transactional
    public Warenbestellung updateWarenbestellung(WarenbestellungId id, Warenbestellung updatedWarenbestellung) {
        Optional<Warenbestellung> existingProdukt = warenbestellungRepository.findById(id);
        if(existingProdukt.isPresent()){
            Warenbestellung wareToUpdate =
                    Warenbestellung.builder()
                            .menge(updatedWarenbestellung.getMenge())
                            .id(updatedWarenbestellung.getId())
                            .build();
            warenbestellungRepository.deleteById(id);
            return warenbestellungRepository.save(wareToUpdate);
        } else {
            throw new IllegalArgumentException("Warenbestellung not found");
        }
    }

    @Transactional
    public void deleteWarenbestellung(WarenbestellungId id) {
        Optional<Warenbestellung> existingProdukt = warenbestellungRepository.findById(id);
        if(existingProdukt.isPresent())
            warenbestellungRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Warenbestellung doesnt exist");
    }


    public List<Warenbestellung> getWarenbestellungenByFiliale(Filiale filiale) {
        return warenbestellungRepository.findAllById_Filiale(filiale);
    }
}
