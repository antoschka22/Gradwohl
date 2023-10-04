package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.service.mitarbeiter.MitarbeiterService;
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
@RequestMapping(path = "mitarbeiter")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class MitarbeiterController {

    private final MitarbeiterService mitarbeiterService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Mitarbeiter>> getAllMitarbeiter(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(mitarbeiterService.getAllMitarbeiter(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mitarbeiter> getMitarbeiterByIs(
            HttpServletRequest request,
            @PathVariable("id") int id) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(mitarbeiterService.getMitarbeiterById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Mitarbeiter> getMitarbeiterByName(
            HttpServletRequest request,
            @PathVariable("name") String name) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(mitarbeiterService.getMitarbeiterByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mitarbeiter> addMitarbeiter(
            @RequestBody Mitarbeiter mitarbeiter,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Mitarbeiter newMitarbeiter = mitarbeiterService.addMitarbeiter(mitarbeiter);
        return new ResponseEntity<>(newMitarbeiter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mitarbeiter> updateMitarbeiter(
            @PathVariable("id") int id,
            @RequestBody Mitarbeiter updatedMitarbeiter,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Mitarbeiter result = mitarbeiterService.updateMitarbeiter(id, updatedMitarbeiter);
        if (result != null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMitarbeiter(
            @PathVariable("id") int id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        mitarbeiterService.deleteMitarbeiter(id);
        return ResponseEntity.noContent().build();
    }
}
