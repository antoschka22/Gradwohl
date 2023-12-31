package at.gradwohl.website.service.nachrichtsenden;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSenden;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSendenId;
import at.gradwohl.website.repository.nachrichtsenden.NachrichtSendenRepository;
import at.gradwohl.website.service.filiale.FilialeService;
import at.gradwohl.website.service.nachricht.NachrichtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NachrichtSendenService {

    private final NachrichtSendenRepository nachrichtSendenRepository;
    private final FilialeService filialeService;
    private final NachrichtService nachrichtService;

    public NachrichtSenden getNachrichtSendenById(NachrichtSendenId id) {
        Optional<NachrichtSenden> existingNachrichtSenden = nachrichtSendenRepository.findById(id);
        if(existingNachrichtSenden.isEmpty())
            throw new IllegalArgumentException("NachrichtSenden existiert nicht");

        return existingNachrichtSenden.get();
    }

    public List<NachrichtSenden> getNachrichtSendenByFiliale(Filiale filiale) {
        Optional<Filiale> existingFiliale = Optional.ofNullable(filialeService.getFilialeById(filiale.getId()));

        if(existingFiliale.isEmpty())
            throw new IllegalArgumentException("Filiale doesnt exist");

        List<NachrichtSenden> nachrichten = nachrichtSendenRepository.getNachrichtSendenByIdFiliale(filiale);

        Collections.sort(nachrichten);
        Collections.reverse(nachrichten);

        return nachrichten;
    }

    public List<NachrichtSenden> getNachrichtSendenByNachrichtId(Nachricht nachricht) {
        Optional<Nachricht> existingNachricht = Optional.ofNullable(nachrichtService.getNachrichtById(nachricht.getId()));

        if(existingNachricht.isEmpty())
            throw new IllegalArgumentException("Nachricht doesnt exist");

        List<NachrichtSenden> nachrichten = nachrichtSendenRepository.getNachrichtSendenByIdNachricht(nachricht);

        return nachrichten;
    }

    public List<NachrichtSenden> addNachrichtenSenden(List<NachrichtSenden> nachrichtSenden) {
        for(NachrichtSenden ns : nachrichtSenden){
            Optional<NachrichtSenden> test = nachrichtSendenRepository.findById(ns.getId());

            if(test.isPresent())
                throw new IllegalArgumentException("NachrichtSenden already exists");
        }

        return nachrichtSendenRepository.saveAll(nachrichtSenden);
    }

    public void deleteNachrichtSenden(NachrichtSendenId id) {
        Optional<NachrichtSenden> optionalNachrichtSenden = nachrichtSendenRepository.findById(id);

        if (optionalNachrichtSenden.isPresent())
            nachrichtSendenRepository.deleteById(id);
        else
            throw new IllegalArgumentException("NachrichtSenden doesn't exist");

    }

    @Transactional
    public NachrichtSenden updateNachrichtSenden(NachrichtSendenId id, NachrichtSenden updatedNachricht) {
        Optional<NachrichtSenden> existingNachricht = nachrichtSendenRepository.findById(id);
        if(existingNachricht.isPresent()){
            NachrichtSenden nachrichtToUpdate =
                    NachrichtSenden.builder()
                            .id(id)
                            .gelesen(updatedNachricht.isGelesen())
                            .build();

            return nachrichtSendenRepository.save(nachrichtToUpdate);
        } else
            throw new IllegalArgumentException("Nachricht not found");

    }
}
