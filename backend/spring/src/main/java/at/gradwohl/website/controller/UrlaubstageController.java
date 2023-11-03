package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.urlaubstage.Urlaubstage;
import at.gradwohl.website.service.urlaubstage.UrlaubstageService;
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
@RequestMapping(path = "urlaubstage")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UrlaubstageController {

    private final JwtService jwtService;
    private final UrlaubstageService urlaubstageService;

    @GetMapping("/{UrlaubstageId}")
    public ResponseEntity<Urlaubstage> getUrlaubstageById(
            HttpServletRequest request,
            @PathVariable("UrlaubstageId") int UrlaubstageId) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(urlaubstageService.getUrlaubstageById(UrlaubstageId).get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Urlaubstage>> getAllUrlaubstage(
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(urlaubstageService.getAllUrlaubstage(), HttpStatus.OK);
    }

    @PostMapping("/urlaubstage")
    public ResponseEntity<List<Urlaubstage>> addUrlaubstage(
            @RequestBody List<Urlaubstage> urlaubstage,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(urlaubstageService.insertUrlaub(urlaubstage), HttpStatus.CREATED);
    }

    @PutMapping("/{urlaubstageId}")
    public ResponseEntity<Urlaubstage> updateUrlaubstage(
            @PathVariable("urlaubstageId") int urlaubstageId,
            @RequestBody Urlaubstage urlaubstage,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        Urlaubstage result = urlaubstageService.updateUrlaubstage(urlaubstageId, urlaubstage);
        if (result != null)
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{urlaubstageId}")
    public ResponseEntity<Void> deleteUrlaubstage(
            @PathVariable("urlaubstageId") int urlaubstageId,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        urlaubstageService.deleteUrlaubstageById(urlaubstageId);
        return ResponseEntity.noContent().build();
    }
}
