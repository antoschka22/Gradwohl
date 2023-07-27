package at.gradwohl.website.model.warenbestellung;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Warenbestellung")
public class Warenbestellung {
    @EmbeddedId
    private WarenbestellungId id;

    @Column(name = "WB_Menge")
    private BigDecimal menge;

}
