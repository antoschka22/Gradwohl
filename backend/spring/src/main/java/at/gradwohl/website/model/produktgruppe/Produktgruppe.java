package at.gradwohl.website.model.produktgruppe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produktgruppe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produktgruppe {
    @Id
    @Column(name = "PG_Name")
    private String name;

    @JsonGetter("name")
    public String getName() {
        return name;
    }
}
