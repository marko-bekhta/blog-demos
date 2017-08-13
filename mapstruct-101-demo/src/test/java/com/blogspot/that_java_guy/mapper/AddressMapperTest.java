package com.blogspot.that_java_guy.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.blogspot.that_java_guy.domain.AddressEntity;
import com.blogspot.that_java_guy.model.Address;

/**
 * @author Marko Bekhta
 */
public class AddressMapperTest {

	private AddressEntity entity;

	@Before
	public void setUp() {
		entity = Samples.addressEntity();
	}

	@Test
	public void constructorTest() {
		Address address = new Address( entity );

		assertAddress( entity, address );
	}

	@Test
	public void staticMethodTest() {
		Address address = Address.from( entity );

		assertAddress( entity, address );
	}

	@Test
	public void toAddressTest() {
		Address address = AddressMapper.INSTANCE.toAddress( entity );

		assertAddress( entity, address );
	}

	static void assertAddress(AddressEntity entity, Address address) {
		assertThat( address ).hasFieldOrPropertyWithValue( "city", entity.getCity() );
		assertThat( address ).hasFieldOrPropertyWithValue( "street", entity.getStreet() );
	}

}