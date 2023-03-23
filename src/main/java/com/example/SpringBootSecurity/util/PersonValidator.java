package com.example.SpringBootSecurity.util;


import com.example.SpringBootSecurity.models.Person;
import com.example.SpringBootSecurity.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try { // это костыль мы проверяем с помощью personDetailsService есть ли в базе человек, ловим ошибку и на ней строим логику ЭТО БАД ПРАКТИК
            personDetailsService.loadUserByUsername(person.getUsername()); // по хорошему надо реализовать новый сервис который будет проверять есть ли человек в базе
        } catch (UsernameNotFoundException ignored){
            return; //все ок пользователь с таким именем не найден
        }
        errors.rejectValue("username", "" , "Опоздал пиздец, имя то занято уже ха ха!");

    }
}
