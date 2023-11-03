package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.dienstplan.DienstplanId;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.service.mitarbeiter.MitarbeiterService;
import at.gradwohl.website.service.dienstplan.DienstplanService;
import at.gradwohl.website.service.filiale.FilialeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name ="jwt-auth")
@RequestMapping(path = "dienstplan")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class DienstplanController {

    private final JwtService jwtService;
    private final DienstplanService dienstplanService;
    private final FilialeService filialeService;
    private final MitarbeiterService mitarbeiterService;
    @GetMapping("/{filialeID}")
    public ResponseEntity<List<Dienstplan>> getDienstplanByFiliale(
            HttpServletRequest request,
            @PathVariable("filialeID") int filialeID) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale = filialeService.getFilialeById(filialeID);

        return new ResponseEntity<>(dienstplanService.getDienstplanByFiliale(filiale), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dienstplan> addDienstplan(
            @RequestBody Dienstplan dienstplan,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(dienstplanService.saveDienstplan(dienstplan), HttpStatus.OK);
    }

    @PutMapping("/{datum}/{filialeId}/{von}/{mitarbeiter}")
    public ResponseEntity<Dienstplan> updateDienstplan(
            @PathVariable("datum") String datum,
            @PathVariable("filialeId") int filialeId,
            @PathVariable("von") String von,
            @PathVariable("mitarbeiter") int mitarbeiterId,
            @RequestBody Dienstplan updatedDienstplan,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsLeiter(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Mitarbeiter mitarbeiter = mitarbeiterService.getMitarbeiterById(mitarbeiterId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        DienstplanId id = new DienstplanId(LocalDate.parse(datum), filiale, LocalTime.parse(von), mitarbeiter);

        Dienstplan result = dienstplanService.updateDienstplan(id, updatedDienstplan);
        if (result != null)
            return ResponseEntity.ok(result);
         else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{datum}/{filialeId}/{von}/{mitarbeiter}")
    public ResponseEntity<Dienstplan> deleteDienstplan(
            @PathVariable("datum") String datum,
            @PathVariable("filialeId") int filialeId,
            @PathVariable("von") String von,
            @PathVariable("mitarbeiter") int mitarbeiterId,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Mitarbeiter mitarbeiter = mitarbeiterService.getMitarbeiterById(mitarbeiterId);
        Filiale filiale = filialeService.getFilialeById(filialeId);

        DienstplanId id = new DienstplanId(LocalDate.parse(datum), filiale, LocalTime.parse(von), mitarbeiter);

        dienstplanService.deleteDienstplan(id);
        return ResponseEntity.noContent().build();
    }

}
