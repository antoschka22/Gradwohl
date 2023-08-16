package at.gradwohl.website.model.produkt;

import at.gradwohl.website.model.lieferbar.Wochentag;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MehlConverter implements AttributeConverter<Mehl, String> {

    @Override
    public String convertToDatabaseColumn(Mehl mehl) { return mehl.name(); }

    @Override
    public Mehl convertToEntityAttribute(String dbData) {
        return Mehl.valueOf(dbData);
    }
}
