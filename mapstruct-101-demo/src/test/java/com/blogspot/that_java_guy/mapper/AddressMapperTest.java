package com.blogspot.that_java_guy.mapper;

import com.blogspot.that_java_guy.domain.AddressEntity;
import com.blogspot.that_java_guy.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Bekhta
 */
class AddressMapperTest {

    private AddressEntity entity;

    @BeforeEach
    void setUp() {
        entity = Samples.addressEntity();
    }

    @Test
    void constructorTest() {
        Address address = new Address(entity);

        assertAddress(entity, address);
    }

    @Test
    void staticMethodTest() {
        Address address = Address.from(entity);

        assertAddress(entity, address);
    }

    @Test
    public void toAddressTest() {
        Address address = AddressMapper.INSTANCE.toAddress(entity);

        assertAddress(entity, address);
    }

    static void assertAddress(AddressEntity entity, Address address) {
        assertThat(address).hasFieldOrPropertyWithValue("city", entity.getCity());
        assertThat(address).hasFieldOrPropertyWithValue("street", entity.getStreet());
    }

}