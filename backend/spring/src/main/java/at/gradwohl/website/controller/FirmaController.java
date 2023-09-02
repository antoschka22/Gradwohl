package at.gradwohl.website.controller;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.service.firma.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "firma")
public class FirmaController {

    private final FirmaService firmaService;

    @Autowired
    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }

    @GetMapping
    public List<Firma> getAllFirma() {
        return firmaService.getAllFirma();
    }

    @PostMapping
    public ResponseEntity<Firma> addFirma(@RequestBody Firma firma) {
        Firma newFirma = firmaService.addFirma(firma);
        return new ResponseEntity<>(newFirma, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Firma> updateFirma(
            @PathVariable("id") String id,
            @RequestBody Firma updatedFirma) {
        Firma result = firmaService.updateFirma(id, updatedFirma);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFirma(@PathVariable("id") String id) {
        firmaService.deleteFirma(id);
        return ResponseEntity.noContent().build();
    }
}
