package ru.danilov.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.danilov.library.dao.PersonDAO;
import ru.danilov.library.models.Person;
import ru.danilov.library.services.PeopleService;

/**
 * User: Nikolai Danilov
 * Date: 22.03.2023
 */
@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (peopleService.findPersonByFioIs(person.getFio()).size() > 0)
            errors.rejectValue("fio", "", "This FIO is already taken");
    }
}
