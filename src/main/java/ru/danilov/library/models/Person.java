package ru.danilov.library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
public class Person {
    private int id;
    //@Pattern(regexp = "[А-Я]\\w+ [А-Я]\\w+ [А-Я]\\w+", message = "Your FIO should be in this format: Surname Name MiddleName")
    private String fio;
    @Min(value = 1900, message = "birth year should be greater than 1900")
    @Max(value = 2023, message = "birth year should be less than 2023")
    private int birthYear;

    public Person() {}

    public Person(int id, String fio, int birth_year) {
        this.id = id;
        this.fio = fio;
        this.birthYear = birth_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
