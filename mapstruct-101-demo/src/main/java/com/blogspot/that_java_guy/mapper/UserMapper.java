package com.blogspot.that_java_guy.mapper;

import com.blogspot.that_java_guy.domain.UserEntity;
import com.blogspot.that_java_guy.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Marko Bekhta
 */

@Mapper(uses = AddressMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "lastVisit", target = "visit")
    @Mapping(source = "userName", target = "credentials.userName")
    @Mapping(source = "password", target = "credentials.password")
    User toUser(UserEntity entity);

    default String genderToString(UserEntity.Gender gender) {
        return gender.equals(UserEntity.Gender.FEMALE) ? "f" : "m";
    }
}
