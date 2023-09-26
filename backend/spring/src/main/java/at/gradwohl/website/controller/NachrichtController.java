package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.service.nachricht.NachrichtService;
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
@RequestMapping(path = "nachricht")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class NachrichtController {
    
    private final JwtService jwtService;
    private final NachrichtService nachrichtService;
    
    
    @GetMapping
    public ResponseEntity<List<Nachricht>> getAllNachrichten(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(nachrichtService.getAllNachricht(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Nachricht> addNachricht(
            @RequestBody Nachricht nachricht,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(nachrichtService.saveNachricht(nachricht), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nachricht> updateNachricht(
            @PathVariable("id") int id,
            @RequestBody Nachricht updatedNachricht,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);


        Nachricht result = nachrichtService.updateNachricht(id, updatedNachricht);
        if (result != null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Nachricht> deleteNachricht(
            @PathVariable("id") int id,
            HttpServletRequest request) {

        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        nachrichtService.deleteNachricht(id);
        return ResponseEntity.noContent().build();
    }
}
