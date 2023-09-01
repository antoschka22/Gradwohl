package at.gradwohl.website.controller;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.warenbestellung.WarenbestellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "warenbestellung")
public class WarenbestellungController {
    private final WarenbestellungService warenbestellungService;
    private final FilialeService filialeService;
    private final ProduktService produktService;

    @Autowired
    public WarenbestellungController(
            WarenbestellungService warenbestellungService,
            FilialeService filialeService,
            ProduktService produktService) {
        this.warenbestellungService = warenbestellungService;
        this.filialeService = filialeService;
        this.produktService = produktService;
    }

    @GetMapping
    public List<Warenbestellung> getAllWarenbestellungen() {
        return warenbestellungService.getAllWarenbestellungen();
    }

    @GetMapping("/date/{datum}")
    public List<Warenbestellung> getWarenbestellungenByDate(@PathVariable("datum") LocalDate datum) {
        return warenbestellungService.getWarenbestellungenByDate(datum);
    }

    @GetMapping("/filiale/{filialeId}")
    public ResponseEntity<List<Warenbestellung>> getWarenbestellungenByFiliale(@PathVariable("filialeId") int filialeId) {
        Filiale filiale = filialeService.getFilialeById(filialeId);
        List<Warenbestellung> warenbestellungen = warenbestellungService.getWarenbestellungenByFiliale(filiale);
        return new ResponseEntity<>(warenbestellungen, HttpStatus.OK);
    }


    @PostMapping
    public List<Warenbestellung> addWarenbestellungen(@RequestBody List<Warenbestellung> warenbestellungen) {
        return warenbestellungService.addWarenbestellungen(warenbestellungen);
    }

    @PutMapping("/{datum}/{produktId}/{filialeId}")
    public ResponseEntity<Warenbestellung> updateWarenbestellung(
            @PathVariable("datum") String datum,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @RequestBody Warenbestellung updatedWarenbestellung) {

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        WarenbestellungId id = new WarenbestellungId(LocalDate.parse(datum), produkt, filiale);

        Warenbestellung result = warenbestellungService.updateWarenbestellung(id, updatedWarenbestellung);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{datum}/{produktId}/{filialeId}")
    public ResponseEntity<Void> deleteWarenbestellung(
            @PathVariable("datum") String datum,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId) {

        Produkt produkt = produktService.getProduktById(produktId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        WarenbestellungId id = new WarenbestellungId(LocalDate.parse(datum), produkt, filiale);

        warenbestellungService.deleteWarenbestellung(id);
        return ResponseEntity.noContent().build();
    }
}

/*
{
    "id": {
      "datum": "2023-08-30",
      "produkt": {
        "id": 66,
    "name": "Bauernbrot",
    "produktgruppe": {
      "name": "VK Brote"
    },
    "bio": true,
    "mehl": "Pharao",
    "hb": false,
    "mehlMischung": "Roggen"
      },
      "filiale": {
        "id":14,
        "filialeName": "Hietzing",
        "filialleiterId": 0,
        "firmaName": "Wien"
      }
    },
    "menge": 1
  }
 */
