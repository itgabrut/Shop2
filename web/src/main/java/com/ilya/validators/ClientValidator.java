package com.ilya.validators;

import com.ilya.model.Client;
import com.ilya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by ilya on 26.09.2016.
 */
@Component
public class ClientValidator  implements Validator {

    @Autowired
    ClientService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","NOT.EMPTY");
        if (client.getName().length() < 2 || client.getName().length() > 32) {
            errors.rejectValue("name", "Size.userForm.username");
        }
        if(client.getSurname().length() < 2 || client.getSurname().length() > 20) errors.rejectValue("surname","SURNAME.FAIL");
        if(client.getAdress().getCountry().isEmpty())errors.rejectValue("adress.country","COUNTRY.EMPT");
        if(client.getAdress().getCity().isEmpty())errors.rejectValue("adress.city","CITY.EMPT");
        if(client.getAdress().getStreet().isEmpty())errors.rejectValue("adress.street","NOT.EMPTY");
        if(client.getAdress().getHouse().isEmpty())errors.rejectValue("adress.house","HOUSE.EMPT");
        if(!client.getSurname().matches("([a-zA-Z]+)||([а-яА-Я]+)")){errors.rejectValue("surname","CHAR.FAIL");}
        if(!client.getName().matches("([a-zA-Z]+)||([а-яА-Я]+)")){errors.rejectValue("name","CHAR.FAIL");}
        if(!client.getEmail().matches("^.+@{1}.+\\..+$") && client.getEmail().length() > 20) errors.rejectValue("email","EMAIL.FAIL");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NOT.EMPTY");
        if(!client.getPassword().equals(client.getPasswordconfirm())) errors.rejectValue("password","NOT.CONFIRMED");
        if (service.getByEmail(client.getEmail()) != null)
            errors.rejectValue("email", "Duplicate.userForm.username");
    }
}
