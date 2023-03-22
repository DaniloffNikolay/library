package ru.danilov.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.danilov.library.dao.BooksDAO;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksDAO booksDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }
}
