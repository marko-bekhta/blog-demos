package com.blogspot.that_java_guy;

import java.util.NoSuchElementException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Marko Bekhta
 */
public class AddressTypeTest {

	@Test
	public void helperMethodsById() throws Exception {
		assertThat( AddressType.HELPER.byId( AddressType.WORK.getId() ) )
				.isEqualTo( AddressType.WORK );

		assertThatExceptionOfType( NoSuchElementException.class )
				.isThrownBy( () -> AddressType.HELPER.byId( Long.MAX_VALUE ) );

		assertThat( AddressType.HELPER.byIdOrDefault( AddressType.WORK.getId(), AddressType.HOME ) )
				.isEqualTo( AddressType.WORK );

		assertThat( AddressType.HELPER.byIdOrDefault( Long.MAX_VALUE, AddressType.HOME ) )
				.isEqualTo( AddressType.HOME );

		assertThat( AddressType.HELPER.byIdOrThrow( AddressType.WORK.getId(), IllegalStateException::new ) )
				.isEqualTo( AddressType.WORK );

		assertThatExceptionOfType( IllegalStateException.class )
				.isThrownBy( () -> AddressType.HELPER.byIdOrThrow( Long.MAX_VALUE, IllegalStateException::new ) );
	}

	@Test
	public void helperMethodsByName() throws Exception {
		assertThat( AddressType.HELPER.byName( AddressType.WORK.getName() ) )
				.isEqualTo( AddressType.WORK );

		assertThatExceptionOfType( NoSuchElementException.class )
				.isThrownBy( () -> AddressType.HELPER.byName( "" ) );

		assertThat( AddressType.HELPER.byNameOrDefault( AddressType.WORK.getName(), AddressType.HOME ) )
				.isEqualTo( AddressType.WORK );

		assertThat( AddressType.HELPER.byNameOrDefault( "", AddressType.HOME ) )
				.isEqualTo( AddressType.HOME );

		assertThat( AddressType.HELPER.byNameOrThrow( AddressType.WORK.getName(), IllegalStateException::new ) )
				.isEqualTo( AddressType.WORK );

		assertThatExceptionOfType( IllegalStateException.class )
				.isThrownBy( () -> AddressType.HELPER.byNameOrThrow( "", IllegalStateException::new ) );
	}
}