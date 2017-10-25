package com.blogspot.that_java_guy;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Bekhta
 */
public class SomeEntityTest extends AbstractEntityTest {
	@Test
	public void simplePersist() throws Exception {
		SomeEntity entity = SomeEntity.from( PhoneType.CELL, AddressType.WORK );

		entityManager.persist( entity );
		entityManager.flush();

		List<Object[]> result = entityManager.createNativeQuery( "SELECT * FROM some_table AS st WHERE st.id = :id" )
				.setParameter( "id", entity.getId() )
				.getResultList();

		assertThat( result ).hasSize( 1 );
		Object[] row = result.get( 0 );
		assertThat( row ).contains(
				entity.getId(),
				PhoneType.CELL.getId(),
				BigInteger.valueOf( AddressType.WORK.getId() )
		);
	}
}