package by.agsr.monitorsensors.dto;

import by.agsr.monitorsensors.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private Role role;
}
