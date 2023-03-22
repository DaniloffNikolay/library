package ru.danilov.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.danilov.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFio(resultSet.getString("fio"));
        person.setBirthYear(resultSet.getInt("birth_year"));
        return person;
    }
}
