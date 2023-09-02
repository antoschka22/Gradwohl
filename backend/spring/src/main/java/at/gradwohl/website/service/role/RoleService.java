package at.gradwohl.website.service.role;

import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import at.gradwohl.website.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository,
                       MitarbeiterRepository mitarbeiterRepository){
        this.roleRepository = roleRepository;
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    public List<MitarbeiterRole> getAllRole() { return roleRepository.findAll(); }

    public MitarbeiterRole getRoleById(String role){
        Optional<MitarbeiterRole> optionalRole =  roleRepository.findById(role);
        if(optionalRole.isPresent())
            return optionalRole.get();
        else
            throw new IllegalArgumentException("Role doesnt exist");
    }

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
