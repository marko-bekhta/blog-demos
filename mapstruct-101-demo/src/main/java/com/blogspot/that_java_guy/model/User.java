package com.blogspot.that_java_guy.model;

import java.time.LocalDate;

/**
 * @author Marko Bekhta
 */
public class User {

    private String firstName;

    private String lastName;

    private Credentials credentials;

    private Address address;

    private int age;

    private String gender;

    private LocalDate visit;

    public User() {
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

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getVisit() {
        return visit;
    }

    public void setVisit(LocalDate visit) {
        this.visit = visit;
    }
}
