package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.dto.UserCreateDto;
import by.agsr.monitorsensors.dto.UserDto;
import by.agsr.monitorsensors.entity.User;
import by.agsr.monitorsensors.mapper.annotation.EncodedMapping;
import org.mapstruct.*;

@Mapper(uses = PasswordEncoderMapper.class)
public interface UserMapper {

    UserDto mapToDto(User user);

    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    User mapToEntity(UserCreateDto userCreateDto);

    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapToUpdate(@MappingTarget User user, UserCreateDto userCreateDto);
}
