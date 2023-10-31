package at.gradwohl.website.service.urlaubstage;

import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaub;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaubId;
import at.gradwohl.website.repository.firmenUrlaub.FirmenUrlaubRepository;
import at.gradwohl.website.repository.urlaubstage.UrlaubgstageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Urlaubstage {

    private UrlaubgstageRepository urlaubgstageRepository;
    private FirmenUrlaubRepository firmenUrlaubRepository;

    public List<FirmenUrlaub> getUrlaubsTageOfFirma(String firma){
        return firmenUrlaubRepository.findByIdFirma(firma);
    }

    @Transactional
    public List<FirmenUrlaub> insertFirmenUrlaub(List<FirmenUrlaub> firmenUrlaub) {
        for(FirmenUrlaub fu : firmenUrlaub){
            Optional<FirmenUrlaub> existingFirmenUrlaub = firmenUrlaubRepository.findById(fu.getId());
            if (existingFirmenUrlaub.isPresent()) {
                throw new IllegalArgumentException("FirmenUrlaub existiert schon");
            }
        }

        return firmenUrlaubRepository.saveAll(firmenUrlaub);
    }

    @Transactional
    public FirmenUrlaub updateFirmenUrlaub(FirmenUrlaubId firmenUrlaubId, FirmenUrlaub updatedFirmenUrlaub) {
        Optional<FirmenUrlaub> existingFirmenUrlaub = firmenUrlaubRepository.findById(firmenUrlaubId);

        if (existingFirmenUrlaub.isPresent()) {
            FirmenUrlaub firmenUrlaubToUpdate = existingFirmenUrlaub.get();
            firmenUrlaubToUpdate.setId(updatedFirmenUrlaub.getId());
            return firmenUrlaubRepository.save(firmenUrlaubToUpdate);
        } else
            throw new IllegalArgumentException("FirmenUrlaub not found");
    }

    @Transactional
    public void deleteFirmenUrlaubById(FirmenUrlaubId firmenUrlaubId) {
        if (!firmenUrlaubRepository.existsById(firmenUrlaubId))
            throw new IllegalArgumentException("FirmenUrlaub with ID " + firmenUrlaubId + " not found");

        firmenUrlaubRepository.deleteById(firmenUrlaubId);
    }

}
