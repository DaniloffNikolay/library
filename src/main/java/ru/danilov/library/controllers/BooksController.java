package ru.danilov.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danilov.library.models.Book;
import ru.danilov.library.models.Person;
import ru.danilov.library.services.BooksService;
import ru.danilov.library.services.PeopleService;

import javax.validation.Valid;
import java.util.List;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
@Controller
@RequestMapping("/books")
public class BooksController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BooksController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        if (book.getPerson() != null) {
            Person personHasBook = book.getPerson();
            model.addAttribute("personHasBook", personHasBook);
        } else {
            List<Person> people = peopleService.findAll();
            model.addAttribute("people", people);
        }
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String addPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        Book book = booksService.findOne(id);
        book.setPerson(person);
        booksService.update(book.getId(), book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/clear")
    public String addPerson(@PathVariable("id") int id) {
        List<Book>  books = booksService.findByBookWherePersonId(id);
        for (Book book : books)
            book.setPerson(null);
        return "redirect:/books";
    }
}
