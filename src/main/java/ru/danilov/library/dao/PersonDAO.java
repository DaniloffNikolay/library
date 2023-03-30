package ru.danilov.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danilov.library.models.Person;

import java.util.List;
import java.util.Optional;

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
        List<Person> people = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        return people; //new PersonMapper()
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, birth_year) VALUES(?, ?)", person.getFio(), person.getBirthYear());
    }

    public Optional<Person> show(String fio) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio=?",
                        new Object[]{fio},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fio=?, birth_year=? WHERE id=?",
                updatedPerson.getFio(), updatedPerson.getBirthYear(), id);
    }
}
