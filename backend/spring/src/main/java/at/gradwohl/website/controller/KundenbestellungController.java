package at.gradwohl.website.controller;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.kundenbestellung.KundenbestellungService;
import at.gradwohl.website.service.produkt.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "kundenbestellung")
public class KundenbestellungController {

    private final KundenbestellungService kundenbestellungService;
    private final FilialeService filialeService;
    private final ProduktService produktService;

    @Autowired
    public KundenbestellungController(
            KundenbestellungService kundenbestellungService,
            FilialeService filialeService,
            ProduktService produktService) {
        this.kundenbestellungService = kundenbestellungService;
        this.filialeService = filialeService;
        this.produktService = produktService;
    }

    @GetMapping
    public List<Kundenbestellung> getAllKundenbestellungen() {
        return kundenbestellungService.getAllKundenbestellung();
    }

    @GetMapping("/date/{datum}")
    public List<Kundenbestellung> getKundenbestellungenByDate(@PathVariable("datum") LocalDate datum) {
        return kundenbestellungService.getKundenbestellungByDate(datum);
    }

    @GetMapping("/filiale/{filialeId}")
    public ResponseEntity<List<Kundenbestellung>> getKundenbestellungenByFiliale(@PathVariable("filialeId") int filialeId) {
        Filiale filiale = filialeService.getFilialeById(filialeId);
        List<Kundenbestellung> kundenbestellungen = kundenbestellungService.getKundenbestellungByFiliale(filiale);
        return new ResponseEntity<>(kundenbestellungen, HttpStatus.OK);
    }


    @PostMapping
    public List<Kundenbestellung> addKundenbestellungen(@RequestBody List<Kundenbestellung> kundenbestellungen) {
        return kundenbestellungService.addKundenbestellung(kundenbestellungen);
    }

    @PutMapping("/{datum}/{produktId}/{filialeId}/{kunde}")
    public ResponseEntity<Kundenbestellung> updateKundenbestellung(
            @PathVariable("datum") String datum,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @PathVariable("kunde") String kunde,
            @RequestBody Kundenbestellung updatedKundenbestellung) {

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
            @PathVariable("kunde") String kunde) {

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        KundenbestellungId id = new KundenbestellungId(LocalDate.parse(datum), produkt, kunde, filiale);

        kundenbestellungService.deleteKundenbestellung(id);
        return ResponseEntity.noContent().build();
    }

}
