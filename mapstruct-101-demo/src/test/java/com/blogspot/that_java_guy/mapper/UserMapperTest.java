package com.blogspot.that_java_guy.mapper;

import static com.blogspot.that_java_guy.mapper.AddressMapperTest.assertAddress;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.blogspot.that_java_guy.domain.UserEntity;
import com.blogspot.that_java_guy.model.User;

/**
 * @author Marko Bekhta
 */
public class UserMapperTest {

    @Test
    public void toUserTest() {
        UserEntity entity = Samples.userEntity();

        User user = UserMapper.INSTANCE.toUser(entity);

        assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("firstName", entity.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", entity.getLastName())
                .hasFieldOrPropertyWithValue("gender", "m")
                .hasFieldOrPropertyWithValue("age", entity.getAge())
                .hasFieldOrPropertyWithValue("visit", entity.getLastVisit());
        assertThat(user.getCredentials())
                .isNotNull()
                .hasFieldOrPropertyWithValue("userName", entity.getUserName())
                .hasFieldOrPropertyWithValue("password", entity.getPassword());

        assertAddress(entity.getAddress(), user.getAddress());
    }

}