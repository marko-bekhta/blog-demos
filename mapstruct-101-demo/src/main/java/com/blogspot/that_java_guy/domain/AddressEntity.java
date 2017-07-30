package com.blogspot.that_java_guy.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Marko Bekhta
 */
@Embeddable
public class AddressEntity implements Serializable {

    private String street;

    private String city;

    public AddressEntity() {
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

}
