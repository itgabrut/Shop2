package com.ilya.validators;

import com.ilya.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by ilya on 02.10.2016.
 */
@Component
public class ClientEditValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","NOT.EMPTY");
        if (client.getName().length() < 2 || client.getName().length() > 32) {errors.rejectValue("name", "Size.userForm.username");}
        if(!client.getSurname().matches("([a-zA-Z]+)||([а-яА-Я]+)")){errors.rejectValue("surname","CHAR.FAIL");}
        if(!client.getName().matches("([a-zA-Z]+)||([а-яА-Я]+)")){errors.rejectValue("name","CHAR.FAIL");}
        if(client.getSurname().length() < 2 || client.getSurname().length() > 20) errors.rejectValue("surname","SURNAME.FAIL");
        if(client.getAdress().getCountry().isEmpty())errors.rejectValue("adress.country","COUNTRY.EMPT");
        if(client.getAdress().getCity().isEmpty())errors.rejectValue("adress.city","CITY.EMPT");
        if(client.getAdress().getHouse().isEmpty())errors.rejectValue("adress.house","HOUSE.EMPT");
    }

}
