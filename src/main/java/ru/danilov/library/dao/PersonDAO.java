package ru.danilov.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danilov.library.models.Person;

import java.util.List;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        /*List<Person> people = jdbcTemplate.query("SELECT * FROM Person", new PersonMapper()); //new BeanPropertyRowMapper<>(Person.class)
        for (Person person : people)
            System.out.println(person);*/
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)); //new PersonMapper()
    }
}
