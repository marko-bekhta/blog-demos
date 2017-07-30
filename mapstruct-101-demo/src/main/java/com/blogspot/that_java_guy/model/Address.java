package com.blogspot.that_java_guy.model;

import com.blogspot.that_java_guy.domain.AddressEntity;

/**
 * @author Marko Bekhta
 */
public class Address {

    private String street;

    private String city;

    public Address() {
    }

    public Address(AddressEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        this.street = entity.getStreet();
        this.city = entity.getCity();
    }

    private Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static Address from(AddressEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        return new Address(entity.getStreet(), entity.getCity());
    }

}
