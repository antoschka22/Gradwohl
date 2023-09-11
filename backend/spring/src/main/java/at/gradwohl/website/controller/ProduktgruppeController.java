package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.produktgruppe.ProduktgruppeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name ="jwt-auth")
@RequestMapping(path = "produktgruppe")
public class ProduktgruppeController {
    private final ProduktgruppeService produktgruppeService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Produktgruppe>> getAllProduktgruppen(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        List<Produktgruppe> gruppen = produktgruppeService.getAllProdukts();
        return new ResponseEntity<>(gruppen, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduktgruppeByName(
            @PathVariable String name,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

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
    public ResponseEntity<Produktgruppe> createProduktgruppe(
            @RequestBody Produktgruppe produktgruppe,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

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
            @RequestBody Produktgruppe updatedProduktgruppe,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        try {
            Produktgruppe updated = produktgruppeService.updateProduktgruppe(id, updatedProduktgruppe);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
