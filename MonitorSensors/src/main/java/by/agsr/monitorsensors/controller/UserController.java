package by.agsr.monitorsensors.controller;

import by.agsr.monitorsensors.dto.UserCreateDto;
import by.agsr.monitorsensors.dto.UserDto;
import by.agsr.monitorsensors.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Find all users")
    public ResponseEntity<List<UserDto>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find by id user")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/registration")
    @Operation(summary = "Create user")
    public ResponseEntity<UserDto> save(@RequestBody UserCreateDto userCreateDto){
        return new ResponseEntity<>(userService.create(userCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<UserDto> update(@PathVariable("id") Integer id,
                                          @RequestBody UserCreateDto userCreateDto){
        return new ResponseEntity<>(userService.update(id, userCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
