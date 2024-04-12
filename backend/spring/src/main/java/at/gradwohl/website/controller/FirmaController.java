package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.service.firma.FirmaService;
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
@RequestMapping(path = "firma")
@CrossOrigin(origins = "*", allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class FirmaController {

    private final FirmaService firmaService;
    private final JwtService jwtService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFirma(
            @PathVariable("id") String id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        firmaService.deleteFirma(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Firma>> getAllFirma(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(firmaService.getAllFirma(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Firma> addFirma(
            @RequestBody Firma firma,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Firma newFirma = firmaService.addFirma(firma);
        return new ResponseEntity<>(newFirma, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Firma> updateFirma(
            @PathVariable("id") String id,
            @RequestBody Firma updatedFirma,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Firma result = firmaService.updateFirma(id, updatedFirma);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
