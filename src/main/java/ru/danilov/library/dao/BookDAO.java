package ru.danilov.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.danilov.library.models.Book;
import ru.danilov.library.models.Person;

import java.util.List;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> index(Person person) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE person_id=?", new Object[]{person.getId()}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Books(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Books SET name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Books SET person_id=? WHERE id=?",
                updatedPerson.getId(), id);
    }

    public void clearPerson(int id) {
        jdbcTemplate.update("UPDATE Books SET person_id=null WHERE id=?", id);
    }
}
