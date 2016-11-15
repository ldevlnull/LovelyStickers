package com.lovelystickersua.validation;

import com.lovelystickersua.POJO.User;
import com.lovelystickersua.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by devnull on 10.11.16.
 */
public class RegisterValidation implements Validator {

    private final UserService uService;


    public RegisterValidation(UserService uService) {
        this.uService = uService;
    }

    @Override
    public boolean supports(Class<?> userClass) {
        return userClass.equals(User.class);
    }

    @Override
    public void validate(Object userClass, Errors errors) {
        User user = (User) userClass;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "This field can not be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "This field can not be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "This field can not be empty!");

/*        if(user.getEmail().isEmpty()) {
            errors.rejectValue("email", "", "This field can't be empty");
        }else if(user.getName().isEmpty() || user.getUsername().isEmpty()){
            errors.rejectValue("username", "", "This field can't be empty");
        }else if(user.getPassword().isEmpty()){
            errors.rejectValue("password", "", "This field can't be empty");
        }*/
        if(uService.findByUsername(user.getName())!=null) {
            errors.rejectValue("username", "", "This username is already taken!");
        }
        if(uService.findByEmail(user.getEmail())!=null) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
