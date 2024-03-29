package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.vorlage.VorlageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name ="jwt-auth")
@RequiredArgsConstructor
@RequestMapping(path = "vorlage")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class VorlageController {

    private final VorlageService vorlageService;
    private final FilialeService filialeService;
    private final ProduktService produktService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Vorlage>> getAllVorlage(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(vorlageService.getAllVorlagen(), HttpStatus.OK);
    }

    @GetMapping("/filiale/{filialeId}")
    public ResponseEntity<List<Vorlage>> getVorlageByFiliale(
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale = filialeService.getFilialeById(filialeId);
        List<Vorlage> vorlagen = vorlageService.getVorlageByFiliale(filiale);
        return new ResponseEntity<>(vorlagen, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Vorlage>> getVorlageById(
            @PathVariable("name") String name,
            HttpServletRequest request){
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        List<Vorlage> vorlagen = vorlageService.getVorlageById(name);
        return new ResponseEntity<>(vorlagen, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Vorlage>> addVorlagen(
            @RequestBody List<Vorlage> vorlagen,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(vorlageService.addVorlage(vorlagen), HttpStatus.OK);
    }

    @PutMapping("/{name}/{produktId}/{filialeId}")
    public ResponseEntity<Vorlage> updateVorlage(
            @PathVariable("name") String name,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @RequestBody Vorlage updatedVorlage,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        VorlageId vorlageId = new VorlageId(name, produkt, filiale);

        Vorlage result = vorlageService.updateVorlage(vorlageId, updatedVorlage);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{name}/{produktId}/{filialeId}")
    public ResponseEntity<Void> deleteVorlage(
            @PathVariable("name") String name,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        VorlageId vorlageId = new VorlageId(name, produkt, filiale);

        vorlageService.deleteVorlage(vorlageId);
        return ResponseEntity.noContent().build();
    }


}
