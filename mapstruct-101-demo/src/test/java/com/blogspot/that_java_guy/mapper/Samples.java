package com.blogspot.that_java_guy.mapper;

import com.blogspot.that_java_guy.domain.AddressEntity;
import com.blogspot.that_java_guy.domain.UserEntity;

import java.time.LocalDate;

/**
 * @author Marko Bekhta
 */
public class Samples {
    static AddressEntity addressEntity() {
        AddressEntity entity = new AddressEntity();
        entity.setCity("my city");
        entity.setStreet("my street");

        return entity;
    }

    static UserEntity userEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setAddress(addressEntity());
        entity.setAge(20);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setPassword("password");
        entity.setUserName("jhon@doe.com");
        entity.setGender(UserEntity.Gender.MALE);
        entity.setLastVisit(LocalDate.now());

        return entity;
    }
}
