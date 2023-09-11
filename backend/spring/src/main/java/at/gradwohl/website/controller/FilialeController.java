package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.service.filiale.FilialeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name ="jwt-auth")
@RequestMapping(path = "filiale")
public class FilialeController {
    private final FilialeService filialeService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Filiale>> getAllFilialen(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(filialeService.getAllFilialen(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiale> getFilialeById(
            @PathVariable("id") int id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Optional<Filiale> filiale = Optional.ofNullable(filialeService.getFilialeById(id));
        return filiale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Filiale> addFiliale(
            @RequestBody Filiale filiale,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale newFiliale = filialeService.addFiliale(filiale);
        return new ResponseEntity<>(newFiliale, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filiale> updateFiliale(
            @PathVariable("id") int id,
            @RequestBody Filiale updatedFiliale,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale result = filialeService.updateFiliale(id, updatedFiliale);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliale(
            @PathVariable("id") int id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        filialeService.deleteFiliale(id);
        return ResponseEntity.noContent().build();
    }
}