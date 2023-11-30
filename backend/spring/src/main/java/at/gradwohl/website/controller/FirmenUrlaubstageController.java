package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaub;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaubId;
import at.gradwohl.website.model.urlaubstage.Urlaubstage;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.urlaubstage.UrlaubstageService;
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
@RequestMapping(path = "urlaubstage")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class FirmenUrlaubstageController {

    private final JwtService jwtService;
    private final UrlaubstageService urlaubstageService;
    private final FilialeService filialeService;

    @GetMapping("/firma/{filialeId}")
    public ResponseEntity<List<FirmenUrlaub>> getFirmenUrlaubByFirma(
            HttpServletRequest request,
            @PathVariable("filialeId") int filialeId) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(urlaubstageService.getUrlaubsTageOfFiliale(filialeId), HttpStatus.OK);
    }

    @PostMapping("/firmenUrlaub")
    public ResponseEntity<List<FirmenUrlaub>> addFirmenUrlaub(
            @RequestBody List<FirmenUrlaub> firmenUrlaub,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(urlaubstageService.insertFirmenUrlaub(firmenUrlaub), HttpStatus.CREATED);
    }

    @PutMapping("/{filialeId}/{urlaubstageId}")
    public ResponseEntity<FirmenUrlaub> updateFirmenUrlaub(
            @PathVariable("filialeId") int filialeId,
            @PathVariable("urlaubstageId") int urlaubstageId,
            @RequestBody FirmenUrlaub firmenUrlaub,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale = filialeService.getFilialeById(filialeId);
        Optional<Urlaubstage> urlaubstage = urlaubstageService.getUrlaubstageById(urlaubstageId);
        FirmenUrlaubId firmenUrlaubId = FirmenUrlaubId.builder()
                .filiale(filiale)
                .urlaubstage(urlaubstage.get())
                .build();
        FirmenUrlaub result = urlaubstageService.updateFirmenUrlaub(firmenUrlaubId, firmenUrlaub);
        if (result != null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{filialeId}/{urlaubstageId}")
    public ResponseEntity<Void> deleteFirmenUrlaub(
            @PathVariable("filialeId") int filialeId,
            @PathVariable("urlaubstageId") int urlaubstageId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale = filialeService.getFilialeById(filialeId);
        Optional<Urlaubstage> urlaubstage = urlaubstageService.getUrlaubstageById(urlaubstageId);
        FirmenUrlaubId firmenUrlaubId = FirmenUrlaubId.builder()
                .filiale(filiale)
                .urlaubstage(urlaubstage.get())
                .build();

        urlaubstageService.deleteFirmenUrlaubById(firmenUrlaubId);
        return ResponseEntity.noContent().build();
    }
}
