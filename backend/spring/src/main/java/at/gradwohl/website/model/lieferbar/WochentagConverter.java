package at.gradwohl.website.model.lieferbar;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class WochentagConverter implements AttributeConverter<Wochentag, String> {

    @Override
    public String convertToDatabaseColumn(Wochentag wochentag) {
        return wochentag.name();
    }

    @Override
    public Wochentag convertToEntityAttribute(String dbData) {
        return Wochentag.valueOf(dbData);
    }
}
