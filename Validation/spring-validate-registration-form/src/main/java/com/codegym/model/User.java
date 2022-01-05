package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;

import javax.validation.constraints.*;
import java.util.Set;

public class User implements Validator {
    @Size(min = 5, max = 45,message = "{error.firstName.size}")
    private String firstName;

    @NotEmpty(message = "{error.lastName.blank}")
    @Size(min = 5, max = 45,message = "{error.lastName.size}")
    private String lastName;

    @Pattern(regexp = "/(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b/")
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$",message =
    "{error.email.pattern}")
    private String email;

    @Min(value = 18,message = "{error.age.outOfValue}")
    private int age;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","error.firstName.empty");
    }
}
