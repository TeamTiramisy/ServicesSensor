package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.mapper.annotation.EncodedMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

    private final PasswordEncoder passwordEncoder;


    @EncodedMapping
    public String encode(String password){
        return passwordEncoder.encode(password);
    }

}
