package at.gradwohl.website.controller;

import at.gradwohl.website.config.AuthenticationService;
import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.controller.model.AuthenticationRequest;
import at.gradwohl.website.controller.model.AuthenticationResponse;
import at.gradwohl.website.controller.model.RegisterRequest;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
            ){
        if(service.authenticate(request) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } else
            return ResponseEntity.ok(service.authenticate(request));
    }
}
