package at.gradwohl.website.service.produktgruppe;

import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.repository.produktgruppe.ProduktgruppeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProduktgruppeService {

    private final ProduktgruppeRepository produktgruppeRepository;

    public List<Produktgruppe> getAllProdukts() {
        return produktgruppeRepository.findAll();
    }

    @Transactional
    public void deleteProduktgruppeByName(String name) {
        if (!produktgruppeRepository.existsById(name)) {
            throw new IllegalArgumentException("Produktgruppe with name " + name + " not found");
        }

        Optional<Produktgruppe> test = produktgruppeRepository.findById(name);
        if(test.isPresent()){
            produktgruppeRepository.setProduktgruppeToNullForProdukte(test.get());
            produktgruppeRepository.deleteById(name);
        }
    }

    @Transactional
    public Produktgruppe createProduktgruppe(Produktgruppe produktgruppe) {
        if(produktgruppeRepository.existsById(produktgruppe.getName())){
            throw new IllegalArgumentException("Produktgruppe exists");
        }

        return produktgruppeRepository.save(produktgruppe);
    }

    //Delete existing key and cascade manually
    @Transactional
    public Produktgruppe updateProduktgruppe(String id, Produktgruppe updatedProduktgruppe) {
        Produktgruppe existingProduktgruppe = produktgruppeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produktgruppe not found"));

        if(produktgruppeRepository.findAll().contains(updatedProduktgruppe.getName()))
            throw new IllegalArgumentException("Produktgruppe already exists");

        produktgruppeRepository.setProduktgruppeToNullForProdukte(existingProduktgruppe);

        produktgruppeRepository.delete(existingProduktgruppe);

        return produktgruppeRepository.save(updatedProduktgruppe);
    }
}
