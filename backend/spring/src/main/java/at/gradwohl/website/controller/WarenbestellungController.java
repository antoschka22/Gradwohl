package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.produkt.ProduktService;
import at.gradwohl.website.service.warenbestellung.WarenbestellungService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@SecurityRequirement(name="jwt-auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(path = "warenbestellung")
public class WarenbestellungController {
    private final WarenbestellungService warenbestellungService;
    private final FilialeService filialeService;
    private final ProduktService produktService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Warenbestellung>> getAllWarenbestellungen(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(warenbestellungService.getAllWarenbestellungen(), HttpStatus.OK);
    }

    @GetMapping("/generate/{datum}/{filiale}")
    public ResponseEntity<List<Warenbestellung>> getGenerateWarenbestellungen(
            HttpServletRequest request,
            @PathVariable("datum") LocalDate datum,
            @PathVariable("filiale") int filiale) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(warenbestellungService.getGenerateWarenbestellungen(datum, filiale), HttpStatus.OK);
    }

    @GetMapping("/date/{datum}")
    public ResponseEntity<List<Warenbestellung>> getWarenbestellungenByDate(
            @PathVariable("datum") LocalDate datum,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(warenbestellungService.getWarenbestellungenByDate(datum), HttpStatus.OK);
    }

    @GetMapping("/filiale/{filialeId}")
    public ResponseEntity<List<Warenbestellung>> getWarenbestellungenByFiliale(
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale = filialeService.getFilialeById(filialeId);
        List<Warenbestellung> warenbestellungen = warenbestellungService.getWarenbestellungenByFiliale(filiale);
        return new ResponseEntity<>(warenbestellungen, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<List<Warenbestellung>> addWarenbestellungen(
            @RequestBody List<Warenbestellung> warenbestellungen,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(warenbestellungService.addWarenbestellungen(warenbestellungen), HttpStatus.OK);
    }

    @PutMapping("/{datum}/{produktId}/{filialeId}")
    public ResponseEntity<Warenbestellung> updateWarenbestellung(
            @PathVariable("datum") String datum,
            @PathVariable("produktId") int produktId,
            @PathVariable("filialeId") int filialeId,
            @RequestBody Warenbestellung updatedWarenbestellung,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

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
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

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
