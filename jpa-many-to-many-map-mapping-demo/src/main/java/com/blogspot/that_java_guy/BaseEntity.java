package com.blogspot.that_java_guy;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * @author Marko Bekhta
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "film_actor_sequence", allocationSize = 1)
	@GeneratedValue(generator = "sequence")
	private Integer id;


	private String uuid = UUID.randomUUID().toString();

	public Integer getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		BaseEntity that = (BaseEntity) o;
		return Objects.equals( uuid, that.uuid );
	}

	@Override
	public int hashCode() {
		return Objects.hash( uuid );
	}
}
