package com.blogspot.that_java_guy.mapper;

import com.blogspot.that_java_guy.domain.AddressEntity;
import com.blogspot.that_java_guy.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Marko Bekhta
 */

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddress(AddressEntity entity);

}
