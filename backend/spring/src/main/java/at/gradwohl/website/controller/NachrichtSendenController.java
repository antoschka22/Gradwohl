package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSenden;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSendenId;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.nachricht.NachrichtService;
import at.gradwohl.website.service.nachrichtsenden.NachrichtSendenService;
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
@RequestMapping(path = "nachrichtSenden/senden")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class NachrichtSendenController {

    private final JwtService jwtService;
    private final NachrichtSendenService nachrichtSendenService;
    private final NachrichtService nachrichtService;
    private final FilialeService filialeService;

    @GetMapping("/{filialeId}/{nachrichtId}")
    public ResponseEntity<NachrichtSenden> getNachrichtById(
            @PathVariable("filialeId") int filialeId,
            @PathVariable("nachrichtId") int nachrichtId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Nachricht nachricht = nachrichtService.getNachrichtById(nachrichtId);
        Filiale filiale =  filialeService.getFilialeById(filialeId);

        NachrichtSendenId nachrichtSendenId =
                NachrichtSendenId.builder()
                        .nachricht(nachricht)
                        .filiale(filiale)
                        .build();

        return new ResponseEntity<>(nachrichtSendenService.getNachrichtSendenById(nachrichtSendenId), HttpStatus.OK);
    }

    @GetMapping("/{filialeId}")
    public ResponseEntity<List<NachrichtSenden>> getNachrichtByFiliale(
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Filiale filiale =  filialeService.getFilialeById(filialeId);

        return new ResponseEntity<>(nachrichtSendenService.getNachrichtSendenByFiliale(filiale), HttpStatus.OK);
    }

    @GetMapping("/nachrichtId/{nachrichtId}")
    public ResponseEntity<List<NachrichtSenden>> getNachrichtByNachrichtId(
            @PathVariable("nachrichtId") int nachrichtId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Nachricht nachricht =  nachrichtService.getNachrichtById(nachrichtId);

        return new ResponseEntity<>(nachrichtSendenService.getNachrichtSendenByNachrichtId(nachricht), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<NachrichtSenden>> addNachrichtToFiliale(
            @RequestBody List<NachrichtSenden> nachrichten,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(nachrichtSendenService.addNachrichtenSenden(nachrichten), HttpStatus.OK);
    }

    @DeleteMapping("/{nachrichtId}/{filialeId}")
    public ResponseEntity<NachrichtSenden> deleteNachrichtSenden(
            @PathVariable("nachrichtId") long nachrichtId,
            @PathVariable("filialeId") int filialeId,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Nachricht nachricht = nachrichtService.getNachrichtById(nachrichtId);
        Filiale filiale = filialeService.getFilialeById(filialeId);
        NachrichtSendenId id = new NachrichtSendenId(filiale, nachricht);

        nachrichtSendenService.deleteNachrichtSenden(id);
        return ResponseEntity.noContent().build();
    }
}
