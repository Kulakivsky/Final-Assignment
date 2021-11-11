package com.example.demo.student;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Person {

    private String firstName;
    private String middleName;
    private String lastName;
//   TODO: review this annotation later
    @Length(min = 3, message = "*Your user name must have at least 3 characters")
    @NotEmpty(message = "*Please provide a user name")
    private String nickName;

    public Person() {
    }

        public Person(String firstName, String middleName, String lastName, String nickName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
