package at.gradwohl.website.controller;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.service.firma.FirmaService;
import at.gradwohl.website.service.lieferbar.LiefebarService;
import at.gradwohl.website.service.produkt.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "lieferbar")
public class LieferbarController {

    private final LiefebarService liefebarService;
    private final FirmaService firmaService;
    private final ProduktService produktService;

    @Autowired
    public LieferbarController(LiefebarService liefebarService,
                               FirmaService firmaService,
                               ProduktService produktService){
        this.liefebarService = liefebarService;
        this.firmaService = firmaService;
        this.produktService = produktService;
    }

    @GetMapping("/{firma}")
    public List<Lieferbar> getLieferbarByFirma(@PathVariable("firma") String firma) {
        Firma firma1 =
                Firma.builder().name(firma).build();
        return liefebarService.getLieferbarByFirma(firma1);
    }

    @PostMapping
    public List<Lieferbar> addLieferbar(@RequestBody List<Lieferbar> lieferbar) {
        return liefebarService.addLieferbar(lieferbar);
    }

    @PutMapping("/{firmaId}/{produktId}")
    public ResponseEntity<Lieferbar> updateLieferbar(
            @PathVariable("firmaId") String firmaId,
            @PathVariable("produktId") int produktId,
            @RequestBody Lieferbar lieferbar) {

        Produkt produkt = produktService.getProduktById(produktId);
        Firma firma = firmaService.getFirmaById(firmaId);

        LieferbarId id = new LieferbarId(produkt, firma);

        Lieferbar result = liefebarService.updateLieferbar(id, lieferbar);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{firmaId}/{produktId}")
    public ResponseEntity<Void> deleteLieferbar(
            @PathVariable("firmaId") String firmaId,
            @PathVariable("produktId") int produktId) {

        Produkt produkt = produktService.getProduktById(produktId);
        Firma firma = firmaService.getFirmaById(firmaId);

        LieferbarId lieferbarId = new LieferbarId(produkt, firma);

        liefebarService.deleteLieferbar(lieferbarId);
        return ResponseEntity.noContent().build();
    }

}
