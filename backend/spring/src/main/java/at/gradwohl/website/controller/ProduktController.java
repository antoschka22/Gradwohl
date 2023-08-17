package at.gradwohl.website.controller;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.service.produkt.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "produkt")
public class ProduktController {

    private final ProduktService produktService;

    @Autowired
    public ProduktController(ProduktService produktService) { this.produktService = produktService; }

    @GetMapping("/{produktgruppeName}")
    public List<Produkt> getProductsByProduktgruppe(@PathVariable String produktgruppeName) {
        return produktService.getProductsByProduktgruppeName(produktgruppeName);
    }

    @GetMapping("/{produktgruppeName}/{hb}")
    public List<Produkt> getProductsByProduktgruppe(
            @PathVariable String produktgruppeName,
            @PathVariable boolean hb) {
        return produktService.getProduktbyGruppeAndHB(produktgruppeName, hb);
    }

    @GetMapping("/hb/{hb}")
    public List<Produkt> getProductsByProduktgruppe(
            @PathVariable boolean hb) {
        return produktService.getHBProdukte(hb);
    }

    @GetMapping
    public  List<Produkt> getAllProducts(){
        return produktService.getAllProdukts();
    }

}
