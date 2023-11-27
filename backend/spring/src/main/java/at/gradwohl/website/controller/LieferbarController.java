package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.service.firma.FirmaService;
import at.gradwohl.website.service.lieferbar.LiefebarService;
import at.gradwohl.website.service.produkt.ProduktService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name ="jwt-auth")
@RequestMapping(path = "lieferbar")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
public class LieferbarController {

    private final LiefebarService liefebarService;
    private final FirmaService firmaService;
    private final ProduktService produktService;
    private final JwtService jwtService;

    @GetMapping("/{firma}")
    public ResponseEntity<List<Lieferbar>> getLieferbarByFirma(
            @PathVariable("firma") String firma,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Firma firma1 =
                Firma.builder().name(firma).build();
        return new ResponseEntity<>(liefebarService.getLieferbarByFirma(firma1), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Lieferbar>> addLieferbar(
            @RequestBody List<Lieferbar> lieferbar,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(liefebarService.addLieferbar(lieferbar), HttpStatus.OK);
    }

    @PutMapping("/{firmaId}/{produktId}")
    public ResponseEntity<Lieferbar> updateLieferbar(
            @PathVariable("firmaId") String firmaId,
            @PathVariable("produktId") int produktId,
            @RequestBody Lieferbar lieferbar,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Produkt produkt = produktService.getProduktById(produktId);
        Firma firma = firmaService.getFirmaById(firmaId);

        LieferbarId id = new LieferbarId(produkt, firma);

        Lieferbar result = liefebarService.updateLieferbar(id, lieferbar);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{firmaId}/{produktId}")
    public ResponseEntity<Void> deleteLieferbar(
            @PathVariable("firmaId") String firmaId,
            @PathVariable("produktId") int produktId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Produkt produkt = produktService.getProduktById(produktId);
        Firma firma = firmaService.getFirmaById(firmaId);

        LieferbarId lieferbarId = new LieferbarId(produkt, firma);

        liefebarService.deleteLieferbar(lieferbarId);
        return ResponseEntity.noContent().build();
    }
}