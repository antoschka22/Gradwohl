package at.gradwohl.website.controller;

import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.produktgruppe.ProduktgruppeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduktgruppeByName(@PathVariable String name) {
        try {
            produktgruppeService.deleteProduktgruppeByName(name);
            return ResponseEntity.ok("Product group deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping
    public ResponseEntity<Produktgruppe> createProduktgruppe(@RequestBody Produktgruppe produktgruppe) {
        try {
            Produktgruppe createdProduktgruppe = produktgruppeService.createProduktgruppe(produktgruppe);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduktgruppe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produktgruppe> updateProduktgruppe(
            @PathVariable String id,
            @RequestBody Produktgruppe updatedProduktgruppe) {
        try {
            Produktgruppe updated = produktgruppeService.updateProduktgruppe(id, updatedProduktgruppe);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
