package by.agsr.monitorsensors.controller;

import by.agsr.monitorsensors.dto.TypeCreateDto;
import by.agsr.monitorsensors.dto.TypeDto;
import by.agsr.monitorsensors.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/types")
@RequiredArgsConstructor
@Tag(name = "Type", description = "Type API")
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    @Operation(summary = "Find all types")
    public ResponseEntity<List<TypeDto>> findAll(){
        return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find by id type")
    public ResponseEntity<TypeDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(typeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/type/{name}")
    @Operation(summary = "Find by id name")
    public ResponseEntity<TypeDto> findById(@PathVariable("name") String name){
        return new ResponseEntity<>(typeService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create type")
    public ResponseEntity<TypeDto> save(@RequestBody TypeCreateDto typeCreateDto){
        return new ResponseEntity<>(typeService.create(typeCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update type")
    public ResponseEntity<TypeDto> update(@PathVariable("id") Integer id,
                                          @RequestBody TypeCreateDto typeCreateDto){
        return new ResponseEntity<>(typeService.update(id, typeCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete type")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        typeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
