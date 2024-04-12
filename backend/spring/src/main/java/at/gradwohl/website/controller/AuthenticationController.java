package at.gradwohl.website.controller;

import at.gradwohl.website.config.AuthenticationService;
import at.gradwohl.website.controller.model.AuthenticationRequest;
import at.gradwohl.website.controller.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AuthenticationController {

    private final AuthenticationService service;

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
