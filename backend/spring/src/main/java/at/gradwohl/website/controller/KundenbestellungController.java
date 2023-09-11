package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.kundenbestellung.KundenbestellungService;
import at.gradwohl.website.service.produkt.ProduktService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name ="jwt-auth")
@RequestMapping(path = "kundenbestellung")
public class KundenbestellungController {

    private final KundenbestellungService kundenbestellungService;
    private final FilialeService filialeService;
    private final ProduktService produktService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Kundenbestellung>> getAllKundenbestellungen(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(kundenbestellungService.getAllKundenbestellung(), HttpStatus.OK);
    }

    @GetMapping("/date/{datum}")
    public ResponseEntity<List<Kundenbestellung>> getKundenbestellungenByDate(
            @PathVariable("datum") LocalDate datum,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(kundenbestellungService.getKundenbestellungByDate(datum), HttpStatus.OK);
    }

    @GetMapping("/filiale/{filialeId}")
    public ResponseEntity<List<Kundenbestellung>> getKundenbestellungenByFiliale(
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale = filialeService.getFilialeById(filialeId);
        List<Kundenbestellung> kundenbestellungen = kundenbestellungService.getKundenbestellungByFiliale(filiale);
        return new ResponseEntity<>(kundenbestellungen, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<List<Kundenbestellung>> addKundenbestellungen(
            @RequestBody List<Kundenbestellung> kundenbestellungen,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(kundenbestellungService.addKundenbestellung(kundenbestellungen), HttpStatus.OK);
    }

    @PutMapping("/{datum}/{produktId}/{filialeId}/{kunde}")
    public ResponseEntity<Kundenbestellung> updateKundenbestellung(
            @PathVariable("datum") String datum,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @PathVariable("kunde") String kunde,
            @RequestBody Kundenbestellung updatedKundenbestellung,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        KundenbestellungId id = new KundenbestellungId(LocalDate.parse(datum), produkt, kunde, filiale);

        Kundenbestellung result = kundenbestellungService.updateKundenbestellung(id, updatedKundenbestellung);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{datum}/{produktId}/{filialeId}/{kunde}")
    public ResponseEntity<Void> deleteKundenbestellung(
            @PathVariable("datum") String datum,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @PathVariable("kunde") String kunde,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        KundenbestellungId id = new KundenbestellungId(LocalDate.parse(datum), produkt, kunde, filiale);

        kundenbestellungService.deleteKundenbestellung(id);
        return ResponseEntity.noContent().build();
    }

}
