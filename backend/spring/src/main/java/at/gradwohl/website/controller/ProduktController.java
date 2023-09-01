package at.gradwohl.website.controller;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.service.produkt.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "produkt")
public class ProduktController {

    private final ProduktService produktService;

    @Autowired
    public ProduktController(ProduktService produktService) { this.produktService = produktService; }

    @PostMapping
    public ResponseEntity<Produkt> insertProduct(@RequestBody Produkt produkt) {
        try {
            Produkt insertedProdukt = produktService.insertProduct(produkt);
            return ResponseEntity.ok(insertedProdukt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produkt> updateProduct(@PathVariable int id, @RequestBody Produkt updatedProdukt) {
        try {
            Produkt updatedProduct = produktService.updateProduct(id, updatedProdukt);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduktById(@PathVariable int id) {
        try {
            produktService.deleteProduktById(id);
            return ResponseEntity.ok("Produkt deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/ware/{id}")
    public ResponseEntity<Produkt> getProductById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(produktService.getProduktById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

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
