package at.gradwohl.website.controller;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.vorlage.VorlageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vorlage")
public class VorlageController {

    private final VorlageService vorlageService;
    private final FilialeService filialeService;
    private final ProduktService produktService;

    @Autowired
    public VorlageController(
            VorlageService vorlageService,
            FilialeService filialeService,
            ProduktService produktService) {
        this.vorlageService = vorlageService;
        this.filialeService = filialeService;
        this.produktService = produktService;
    }

    @GetMapping
    public List<Vorlage> getAllVorlage() {
        return vorlageService.getAllVorlagen();
    }

    @GetMapping("/filiale/{filialeId}")
    public ResponseEntity<List<Vorlage>> getVorlageByFiliale(@PathVariable("filialeId") int filialeId) {
        Filiale filiale = filialeService.getFilialeById(filialeId);
        List<Vorlage> vorlagen = vorlageService.getVorlageByFiliale(filiale);
        return new ResponseEntity<>(vorlagen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Vorlage>> getVorlageById(@PathVariable("id") int id){
        List<Vorlage> vorlagen = vorlageService.getVorlageById(id);
        return new ResponseEntity<>(vorlagen, HttpStatus.OK);
    }

    @PostMapping
    public List<Vorlage> addVorlageen(@RequestBody List<Vorlage> vorlageen) {
        return vorlageService.addVorlage(vorlageen);
    }

    @PutMapping("/{id}/{produktId}/{filialeId}")
    public ResponseEntity<Vorlage> updateVorlage(
            @PathVariable("id") int id,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @RequestBody Vorlage updatedVorlage) {

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        VorlageId vorlageId = new VorlageId(id, produkt, filiale);

        Vorlage result = vorlageService.updateVorlage(vorlageId, updatedVorlage);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}/{produktId}/{filialeId}")
    public ResponseEntity<Void> deleteVorlage(
            @PathVariable("id") int id,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId) {

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        VorlageId vorlageId = new VorlageId(id, produkt, filiale);

        vorlageService.deleteVorlage(vorlageId);
        return ResponseEntity.noContent().build();
    }


}
