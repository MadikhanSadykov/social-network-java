package com.madikhan.app.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Birthday {

    private LocalDate birthDate;

    public Birthday() {
    }

    public Birthday(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
