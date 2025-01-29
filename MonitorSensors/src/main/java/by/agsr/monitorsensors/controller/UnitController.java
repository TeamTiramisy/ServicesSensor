package by.agsr.monitorsensors.controller;

import by.agsr.monitorsensors.dto.UnitCreateDto;
import by.agsr.monitorsensors.dto.UnitDto;
import by.agsr.monitorsensors.service.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/units")
@RequiredArgsConstructor
@Tag(name = "Unit", description = "Unit API")
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    @Operation(summary = "Find all units")
    public ResponseEntity<List<UnitDto>> findAll(){
        return new ResponseEntity<>(unitService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find by id unit")
    public ResponseEntity<UnitDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(unitService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/unit/{name}")
    @Operation(summary = "Find by name unit")
    public ResponseEntity<UnitDto> findById(@PathVariable("name") String name){
        return new ResponseEntity<>(unitService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create unit")
    public ResponseEntity<UnitDto> save(@RequestBody UnitCreateDto unitCreateDto){
        return new ResponseEntity<>(unitService.create(unitCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update unit")
    public ResponseEntity<UnitDto> update(@PathVariable("id") Integer id,
                                          @RequestBody UnitCreateDto unitCreateDto){
        return new ResponseEntity<>(unitService.update(id, unitCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete unit")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        unitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
