package at.gradwohl.website.service.produkt;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.repository.produkt.ProduktRepository;
import at.gradwohl.website.repository.produktgruppe.ProduktgruppeRepository;
import at.gradwohl.website.service.produktgruppe.ProduktgruppeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProduktService {


    private final ProduktRepository produktRepository;
    private final ProduktgruppeRepository produktgruppeRepository;

    public List<Produkt> getProductsByProduktgruppeName(String produktgruppeName) {
        return produktRepository.findByProduktgruppe_Name(produktgruppeName);
    }

    public List<Produkt> getAllProdukts(){
        return  produktRepository.findAll();
    }

    public List<Produkt> getProduktbyGruppeAndHB(String produktgruppeName, boolean hb){
        return produktRepository.findByGruppeAndHb(produktgruppeName, hb);
    }

    public List<Produkt> getHBProdukte(boolean hb){
        return produktRepository.findByHb(hb);
    }

    public Produkt getProduktById(int id) {
        Produkt test = produktRepository.findById(id);
        if(test == null){
            throw new IllegalArgumentException("Produkt existiert nicht");
        }
        return produktRepository.findById(id);
    }

    @Transactional
    public Produkt insertProduct(Produkt produkt) {
        Optional<Produkt> existingProdukt = Optional.ofNullable(produktRepository.findById(produkt.getId()));
        if (existingProdukt.isPresent()) {
            throw new IllegalArgumentException("Produkt existiert schon");
        }

        String produktgruppeName = produkt.getProduktgruppe().getName();
        Optional<Produktgruppe> existingProduktgruppe = produktgruppeRepository.findByName(produktgruppeName);
        if (existingProduktgruppe.isEmpty()) {
            throw new IllegalArgumentException("Produktgruppe existiert nicht");
        }

        return produktRepository.save(produkt);
    }

    @Transactional
    public Produkt updateProduct(int productId, Produkt updatedProdukt) {
        Optional<Produkt> existingProdukt = Optional.ofNullable(produktRepository.findById(productId));

        if (existingProdukt.isPresent()) {
            Produkt produktToUpdate = existingProdukt.get();
            produktToUpdate.setName(updatedProdukt.getName());
            produktToUpdate.setBio(updatedProdukt.isBio());
            produktToUpdate.setHb(updatedProdukt.isHb());
            produktToUpdate.setMehl(updatedProdukt.getMehl());
            produktToUpdate.setMehlMischung(updatedProdukt.getMehlMischung());
            produktToUpdate.setProduktgruppe(updatedProdukt.getProduktgruppe());
            return produktRepository.save(produktToUpdate);
        } else {
            throw new IllegalArgumentException("Produkt not found");
        }
    }

    @Transactional
    public void deleteProduktById(int id) {
        if (!produktRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }

        produktRepository.deleteById(id);
    }
}
