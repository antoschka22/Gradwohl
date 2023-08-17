package at.gradwohl.website.service.produkt;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.repository.produkt.ProduktRepository;
import at.gradwohl.website.service.produktgruppe.ProduktgruppeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduktService {


    private final ProduktRepository produktRepository;

    @Autowired
    public ProduktService(ProduktRepository produktRepository) { this.produktRepository = produktRepository; }

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
}
