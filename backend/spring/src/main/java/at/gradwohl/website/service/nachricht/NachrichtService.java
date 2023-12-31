package at.gradwohl.website.service.nachricht;

import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.repository.nachricht.NachrichtRepository;
import at.gradwohl.website.repository.nachrichtsenden.NachrichtSendenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NachrichtService {

    private final NachrichtRepository nachrichtRepository;
    private final NachrichtSendenRepository nachrichtSendenRepository;

    public List<Nachricht> getAllNachricht() {
        return nachrichtRepository.findAllByOrderByDatumDesc();
    }

    public Nachricht getNachrichtById(long id) {
        Optional<Nachricht> nachrichtOptional = nachrichtRepository.findById(id);
        
        if(!nachrichtOptional.isPresent())
            throw new IllegalArgumentException("Id doesnt exist");
        
        return nachrichtOptional.get();
    }

    public Nachricht saveNachricht(Nachricht nachricht) {
        Optional<Nachricht> nachrichtsVorhanden = nachrichtRepository.findById(nachricht.getId());

        if(nachrichtsVorhanden.isPresent())
            throw new IllegalArgumentException("Exists already");

        return nachrichtRepository.save(nachricht);
    }

    @Transactional
    public Nachricht updateNachricht(long id, Nachricht updatedNachricht) {
        Optional<Nachricht> existingNachricht = nachrichtRepository.findById(id);
        if(existingNachricht.isPresent()){
            Nachricht nachrichtToUpdate =
                    Nachricht.builder()
                            .id(id)
                            .nachricht(updatedNachricht.getNachricht())
                            .build();

            return nachrichtRepository.save(nachrichtToUpdate);
        } else
            throw new IllegalArgumentException("Nachricht not found");

    }

    @Transactional
    public void deleteNachricht(long id) {
        Optional<Nachricht> existingNachricht = nachrichtRepository.findById(id);
        if(existingNachricht.isPresent()){
            nachrichtSendenRepository.deleteNachrichtFromInput(existingNachricht.get());
            nachrichtRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("Nachricht doesnt exist");
    }
}