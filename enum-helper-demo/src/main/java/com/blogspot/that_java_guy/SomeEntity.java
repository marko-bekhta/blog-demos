package com.blogspot.that_java_guy;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.blogspot.that_java_guy.converters.AddressTypeAttributeConverter;
import com.blogspot.that_java_guy.converters.PhoneTypeAttributeConverter;

/**
 * @author Marko Bekhta
 */
@Entity
@Table(name = "some_table")
public class SomeEntity {

	@Id
	@GeneratedValue
	private int id;

	@Convert(converter = PhoneTypeAttributeConverter.class)
	private PhoneType phoneType;

	@Convert(converter = AddressTypeAttributeConverter.class)
	private AddressType addressType;

	protected SomeEntity() {
	}

	private SomeEntity(PhoneType phoneType, AddressType addressType) {
		this.phoneType = phoneType;
		this.addressType = addressType;
	}

	public static SomeEntity from(PhoneType phoneType, AddressType addressType) {
		return new SomeEntity( phoneType, addressType );
	}

	public int getId() {
		return id;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public AddressType getAddressType() {
		return addressType;
	}
}
