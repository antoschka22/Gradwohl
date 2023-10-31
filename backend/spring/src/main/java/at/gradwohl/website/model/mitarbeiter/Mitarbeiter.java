package at.gradwohl.website.model.mitarbeiter;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Mitarbeiter")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mitarbeiter implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_ID")
    private int id;

    @Column(name = "M_Name", unique = true)
    private String name;

    @Column(name = "M_Passwort")
    private String password;

    @Column(name= "M_Springer")
    private boolean springer;

    @ManyToOne
    @JoinColumn(name = "M_Role", referencedColumnName = "MR_Role")
    private MitarbeiterRole role;

    @ManyToOne
    @JoinColumn(name = "M_Filiale", referencedColumnName = "F_ID")
    private Filiale filiale;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

