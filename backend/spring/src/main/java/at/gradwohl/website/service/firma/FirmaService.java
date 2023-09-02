package at.gradwohl.website.service.kundenbestellung;

import at.gradwohl.website.repository.firma.FirmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmaService {

    private final FirmaRepository firmaRepository;

    @Autowired
    public FirmaService(FirmaRepository firmaRepository){
        this.firmaRepository = firmaRepository;
    }


}
