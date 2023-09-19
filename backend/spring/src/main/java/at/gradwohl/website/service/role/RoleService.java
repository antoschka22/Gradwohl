package at.gradwohl.website.service.role;

import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import at.gradwohl.website.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final MitarbeiterRepository mitarbeiterRepository;

    public List<MitarbeiterRole> getAllRole() { return roleRepository.findAll(); }

    public MitarbeiterRole addRole(MitarbeiterRole role) {
        Optional<MitarbeiterRole> optionalRole = roleRepository.findById(role.getRole());

        if(optionalRole.isPresent())
            throw new IllegalArgumentException("Role already exists");

        return roleRepository.save(role);
    }

    public MitarbeiterRole updateRole(String id, MitarbeiterRole updatedRole) {
        Optional<MitarbeiterRole> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            mitarbeiterRepository.updateRoleToNull(optionalRole.get());
            roleRepository.deleteById(id);
            return roleRepository.save(updatedRole);
        } else
            throw new IllegalArgumentException("Empty Body");
    }

    public void deleteRole(String id) {
        Optional<MitarbeiterRole> optionalRole = roleRepository.findById(id);

        if (optionalRole.isPresent()) {
            mitarbeiterRepository.updateRoleToNull(optionalRole.get());
            roleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Role doesn't exist");
        }
    }
}
