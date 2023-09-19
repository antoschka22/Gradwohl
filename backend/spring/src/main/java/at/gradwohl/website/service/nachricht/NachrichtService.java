package at.gradwohl.website.service.nachricht;

import at.gradwohl.website.repository.nachricht.NachrichtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NachrichtService {

    private final NachrichtRepository nachrichtRepository;


}
