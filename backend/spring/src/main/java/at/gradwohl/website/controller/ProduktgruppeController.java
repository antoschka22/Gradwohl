package at.gradwohl.website.controller.produktgruppe;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.produktgruppe.ProduktgruppeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "produktgruppe")
public class ProduktgruppeController {
    private final ProduktgruppeService produktgruppeService;

    @Autowired
    public ProduktgruppeController(ProduktgruppeService ProduktgruppeService) { this.produktgruppeService = ProduktgruppeService; }

    @GetMapping
    public List<Produktgruppe> getAllProduktgruppen() {
        List<Produktgruppe> gruppen = produktgruppeService.getAllProdukts();
        return gruppen;
    }
}
