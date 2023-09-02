package at.gradwohl.website.service.firma;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.firma.FirmaRepository;
import at.gradwohl.website.repository.lieferbar.LieferbarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirmaService {

    private final FirmaRepository firmaRepository;
    private final FilialeRepository filialeRepository;
    private final LieferbarRepository lieferbarRepository;

    @Autowired
    public FirmaService(FirmaRepository firmaRepository,
                        FilialeRepository filialeRepository,
                        LieferbarRepository lieferbarRepository){
        this.firmaRepository = firmaRepository;
        this.filialeRepository = filialeRepository;
        this.lieferbarRepository = lieferbarRepository;
    }

    public List<Firma> getAllFirma() {
        return firmaRepository.findAll();
    }

    public Firma getFirmaById(String firma){
        Optional<Firma> optionalFirma =  firmaRepository.findById(firma);
        if(optionalFirma.isPresent())
            return optionalFirma.get();
        else
            throw new IllegalArgumentException("Firma doesnt exist");
    }

    public Firma addFirma(Firma firma) {
        Optional<Firma> optionalFirma = firmaRepository.findById(firma.getName());

        if(optionalFirma.isPresent())
            throw new IllegalArgumentException("Firma already exists");

        return firmaRepository.save(firma);
    }

    public Firma updateFirma(String id, Firma updatedFirma) {
        Optional<Firma> optionalFirma = firmaRepository.findById(id);
        if(optionalFirma.isPresent()){
            filialeRepository.setFirmaToNull(optionalFirma.get());
            lieferbarRepository.deleteByFirma(optionalFirma.get());
            firmaRepository.deleteById(id);
            return firmaRepository.save(updatedFirma);
        } else
            throw new IllegalArgumentException("Empty Body");
    }

    public void deleteFirma(String id) {
        Optional<Firma> optionalFirma = firmaRepository.findById(id);

        if (optionalFirma.isPresent()) {
            Firma firma = optionalFirma.get();
            filialeRepository.setFirmaToNull(firma);
            lieferbarRepository.deleteByFirma(firma);
            firmaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Firma doesn't exist");
        }
    }

}
