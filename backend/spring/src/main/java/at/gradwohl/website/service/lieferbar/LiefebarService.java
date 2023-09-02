package at.gradwohl.website.service.lieferbar;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import at.gradwohl.website.repository.lieferbar.LieferbarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiefebarService {

    private final LieferbarRepository lieferbarRepository;

    @Autowired
    public LiefebarService(LieferbarRepository lieferbarRepository){
        this.lieferbarRepository = lieferbarRepository;
    }

    public List<Lieferbar> getLieferbarByFirma(Firma firma) {
        return lieferbarRepository.findAllById_Firma(firma);
    }

    @Transactional
    public List<Lieferbar> addLieferbar(List<Lieferbar> lieferbar) {
        List<Lieferbar> lieferbarVorhanden = lieferbarRepository.findAll();
        for (Lieferbar l : lieferbar) {
            if (lieferbarVorhanden.contains(l))
                throw new IllegalArgumentException("Already exists");

        }
        return lieferbarRepository.saveAll(lieferbar);
    }

    @Transactional
    public Lieferbar updateLieferbar(LieferbarId id, Lieferbar updatedlieferbar) {
        Optional<Lieferbar> existingProdukt = lieferbarRepository.findById(id);
        if(existingProdukt.isPresent()){
            Lieferbar wareToUpdate =
                    Lieferbar.builder()
                            .id(updatedlieferbar.getId())
                            .bis(updatedlieferbar.getBis())
                            .von(updatedlieferbar.getVon())
                            .build();
            lieferbarRepository.deleteById(id);
            return lieferbarRepository.save(wareToUpdate);
        } else {
            throw new IllegalArgumentException("lieferbar not found");
        }
    }
    
    @Transactional
    public void deleteLieferbar(LieferbarId id) {
        Optional<Lieferbar> existingProdukt = lieferbarRepository.findById(id);
        if(existingProdukt.isPresent())
            lieferbarRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Warenbestellung doesnt exist");
    }


}
