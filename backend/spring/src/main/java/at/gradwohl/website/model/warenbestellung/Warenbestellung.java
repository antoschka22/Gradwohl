package at.gradwohl.website.model.warenbestellung;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Warenbestellung")
public class Warenbestellung {
    @EmbeddedId
    private WarenbestellungId id;

    @Column(name = "WB_Menge")
    private double menge;

}
