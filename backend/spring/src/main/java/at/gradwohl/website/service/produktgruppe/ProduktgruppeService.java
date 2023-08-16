package at.gradwohl.website.service.produktgruppe;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.repository.produktgruppe.ProduktgruppeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduktgruppeService {

    private final ProduktgruppeRepository produktgruppeRepository;

    @Autowired
    public ProduktgruppeService(ProduktgruppeRepository produktgruppeRepository) { this.produktgruppeRepository = produktgruppeRepository; }

    public List<Produktgruppe> getAllProdukts() {
        return produktgruppeRepository.findAll();
    }
}
