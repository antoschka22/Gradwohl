package at.gradwohl.website.service.urlaubstage;

import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaub;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaubId;
import at.gradwohl.website.model.urlaubstage.Urlaubstage;
import at.gradwohl.website.repository.firma.FirmaRepository;
import at.gradwohl.website.repository.firmenUrlaub.FirmenUrlaubRepository;
import at.gradwohl.website.repository.urlaubstage.UrlaubgstageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlaubstageService {

    private final UrlaubgstageRepository urlaubgstageRepository;
    private final FirmenUrlaubRepository firmenUrlaubRepository;
    private final FirmaRepository firmaRepository;

    public List<FirmenUrlaub> getUrlaubsTageOfFirma(String firmaId){
        Firma firma = firmaRepository.getById(firmaId);
        return firmenUrlaubRepository.findByIdFirma(firma);
    }

    public Optional<Urlaubstage> getUrlaubstageById(int id){
        return urlaubgstageRepository.findById(id);
    }

    public List<Urlaubstage> getAllUrlaubstage(){
        return urlaubgstageRepository.findAll();
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
    public List<Urlaubstage> insertUrlaub(List<Urlaubstage> urlaubstage) {
        for(Urlaubstage ut : urlaubstage){
            Optional<Urlaubstage> existingFirmenUrlaub = urlaubgstageRepository.findById(ut.getId());
            if (existingFirmenUrlaub.isPresent()) {
                throw new IllegalArgumentException("Urlaubstag existiert schon");
            }
        }

        return urlaubgstageRepository.saveAll(urlaubstage);
    }

    @Transactional
    public FirmenUrlaub updateFirmenUrlaub(FirmenUrlaubId id, FirmenUrlaub updatedFirmenUrlaub) {
        Optional<FirmenUrlaub> existingFirmenUrlaub = firmenUrlaubRepository.findById(id);

        if (existingFirmenUrlaub.isPresent()) {
            deleteFirmenUrlaubById(id);
            return firmenUrlaubRepository.save(updatedFirmenUrlaub);
        } else
            throw new IllegalArgumentException("FirmenUrlaub not found");
    }

    @Transactional
    public Urlaubstage updateUrlaubstage(int id, Urlaubstage updatedUrlaubstage) {
        Optional<Urlaubstage> existingUrlaubstage = urlaubgstageRepository.findById(id);

        if (existingUrlaubstage.isPresent()) {
            deleteUrlaubstageById(id);
            return urlaubgstageRepository.save(updatedUrlaubstage);
        } else
            throw new IllegalArgumentException("FirmenUrlaub not found");
    }

    @Transactional
    public void deleteFirmenUrlaubById(FirmenUrlaubId firmenUrlaubId) {
        if (!firmenUrlaubRepository.existsById(firmenUrlaubId))
            throw new IllegalArgumentException("FirmenUrlaub with ID " + firmenUrlaubId + " not found");

        firmenUrlaubRepository.deleteById(firmenUrlaubId);
    }

    @Transactional
    public void deleteUrlaubstageById(int id) {
        if (!urlaubgstageRepository.existsById(id))
            throw new IllegalArgumentException("FirmenUrlaub with ID " + id + " not found");

        firmenUrlaubRepository.deleteByUrlaubstag(getUrlaubstageById(id).get());
        urlaubgstageRepository.deleteById(id);
    }

}
