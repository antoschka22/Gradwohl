package at.gradwohl.website.config;

import at.gradwohl.website.controller.model.AuthenticationRequest;
import at.gradwohl.website.controller.model.AuthenticationResponse;
import at.gradwohl.website.controller.model.RegisterRequest;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MitarbeiterRepository mitarbeiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        MitarbeiterRole role = MitarbeiterRole.builder()
                .role(request.getRole())
                .build();
        var mitarbeiter = Mitarbeiter.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        mitarbeiterRepository.save(mitarbeiter);
        var jwtToken = jwtService.generateToken(mitarbeiter);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = mitarbeiterRepository.findByName(request.getName())
                .orElseThrow();
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }else {
            return null;
        }

    }
}
