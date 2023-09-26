package at.gradwohl.website.controller;

import at.gradwohl.website.config.AuthenticationService;
import at.gradwohl.website.controller.model.AuthenticationRequest;
import at.gradwohl.website.controller.model.AuthenticationResponse;
import at.gradwohl.website.controller.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AuthenticationController {

    private final AuthenticationService service;

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

    @GetMapping("/test")
    public String test() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://ai_python:4000"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("---------------------");
        System.out.println("Response from Python server: " + response.body());

        return response.body();
    }
}
