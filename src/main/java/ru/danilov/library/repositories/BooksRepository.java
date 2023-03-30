package ru.danilov.library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilov.library.models.Book;
import ru.danilov.library.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByPerson(Person person);
    //List<Book> findByBookWherePersonId(int id);
}