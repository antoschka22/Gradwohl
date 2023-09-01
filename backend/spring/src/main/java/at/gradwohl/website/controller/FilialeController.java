package at.gradwohl.website.controller;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.service.filiale.FilialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "filiale")
public class FilialeController {
    private final FilialeService filialeService;

    @Autowired
    public FilialeController(FilialeService filialeService) {
        this.filialeService = filialeService;
    }

    @GetMapping
    public List<Filiale> getAllFilialen() {
        return filialeService.getAllFilialen();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiale> getFilialeById(@PathVariable("id") int id) {
        Optional<Filiale> filiale = Optional.ofNullable(filialeService.getFilialeById(id));
        return filiale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Filiale> addFiliale(@RequestBody Filiale filiale) {
        Filiale newFiliale = filialeService.addFiliale(filiale);
        return new ResponseEntity<>(newFiliale, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filiale> updateFiliale(
            @PathVariable("id") int id,
            @RequestBody Filiale updatedFiliale) {
        Filiale result = filialeService.updateFiliale(id, updatedFiliale);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliale(@PathVariable("id") int id) {
        filialeService.deleteFiliale(id);
        return ResponseEntity.noContent().build();
    }
}