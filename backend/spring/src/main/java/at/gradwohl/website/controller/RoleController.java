package at.gradwohl.website.controller;

import at.gradwohl.website.config.JwtService;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.service.role.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name ="jwt-auth")
@RequestMapping(path = "role")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class RoleController {

    private final RoleService roleService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<MitarbeiterRole>> getAllRole(HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsVerkauf(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MitarbeiterRole> addRole(
            @RequestBody MitarbeiterRole role,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        MitarbeiterRole newRole = roleService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MitarbeiterRole> updateRole(
            @PathVariable("id") String id,
            @RequestBody MitarbeiterRole updatedRole,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        MitarbeiterRole result = roleService.updateRole(id, updatedRole);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable("id") String id,
            HttpServletRequest request) {
        String myHeader = request.getHeader("Authorization").substring(7);
        if(!jwtService.getRoleIsZentrale(myHeader))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
