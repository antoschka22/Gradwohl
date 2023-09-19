package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.service.produkt.ProduktService;
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
@RequestMapping(path = "produkt")
public class ProduktController {

    private final ProduktService produktService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<Produkt> insertProduct(
            @RequestBody Produkt produkt,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        try {
            Produkt insertedProdukt = produktService.insertProduct(produkt);
            return ResponseEntity.ok(insertedProdukt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produkt> updateProduct(
            @PathVariable int id,
            @RequestBody Produkt updatedProdukt,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        try {
            Produkt updatedProduct = produktService.updateProduct(id, updatedProdukt);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduktById(
            @PathVariable int id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        try {
            produktService.deleteProduktById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/ware/{id}")
    public ResponseEntity<Produkt> getProductById(
            @PathVariable int id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        try{
            return ResponseEntity.ok(produktService.getProduktById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{produktgruppeName}")
    public ResponseEntity<List<Produkt>> getProductsByProduktgruppe(
            @PathVariable String produktgruppeName,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(produktService.getProductsByProduktgruppeName(produktgruppeName), HttpStatus.OK);
    }

    @GetMapping("/{produktgruppeName}/{hb}")
    public ResponseEntity<List<Produkt>> getProductsByProduktgruppe(
            @PathVariable String produktgruppeName,
            @PathVariable boolean hb,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(produktService.getProduktbyGruppeAndHB(produktgruppeName, hb), HttpStatus.OK);
    }

    @GetMapping("/hb/{hb}")
    public ResponseEntity<List<Produkt>> getProductsByProduktgruppe(
            @PathVariable boolean hb,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(produktService.getHBProdukte(hb), HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<Produkt>> getAllProducts(HttpServletRequest request){
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(produktService.getAllProdukts(), HttpStatus.OK);
    }

}
