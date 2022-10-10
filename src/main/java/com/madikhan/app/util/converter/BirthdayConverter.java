package com.madikhan.app.util.converter;

import com.madikhan.app.model.Birthday;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.util.Optional;

public class BirthdayConverter implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday birthdayEntity) {
        return Optional.ofNullable(birthdayEntity)
                .map(Birthday::getBirthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date dateSql) {
        return Optional.ofNullable(dateSql)
                .map(Date::toLocalDate)
                .map(Birthday::new)
                .orElse(null);
    }
}
