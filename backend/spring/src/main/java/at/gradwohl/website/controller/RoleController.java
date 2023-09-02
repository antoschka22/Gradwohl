package at.gradwohl.website.controller;

import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public List<MitarbeiterRole> getAllRole() {
        return roleService.getAllRole();
    }

    @PostMapping
    public ResponseEntity<MitarbeiterRole> addRole(@RequestBody MitarbeiterRole role) {
        MitarbeiterRole newRole = roleService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MitarbeiterRole> updateRole(
            @PathVariable("id") String id,
            @RequestBody MitarbeiterRole updatedRole) {
        MitarbeiterRole result = roleService.updateRole(id, updatedRole);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
