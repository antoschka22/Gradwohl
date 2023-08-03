package at.gradwohl.website.model.nachricht;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Nachricht")
public class Nachricht {
    @Id
    @Column(name = "N_ID")
    private int id;

    @Column(name = "N_Nachricht", columnDefinition = "TEXT") // For JSON data type
    private String nachricht;

    @Getter(onMethod = @__({@JsonInclude}))
    private transient List<String> paragraphs; // Transient field for deserialization

    // Getter and setter for 'nachricht' (for direct access)

    @PrePersist
    @PostLoad
    private void updateParagraphs() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            paragraphs = objectMapper.readValue(nachricht, ArrayList.class);
        } catch (JsonProcessingException e) {
            paragraphs = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            nachricht = objectMapper.writeValueAsString(paragraphs);
        } catch (JsonProcessingException e) {
            nachricht = "[]";
            e.printStackTrace();
        }
    }
}
