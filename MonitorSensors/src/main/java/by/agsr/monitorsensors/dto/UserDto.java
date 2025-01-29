package by.agsr.monitorsensors.dto;

import by.agsr.monitorsensors.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Integer id;

    String username;

    String firstname;

    String lastname;

    Role role;
}
