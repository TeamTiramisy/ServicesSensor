package by.agsr.monitorsensors.controller;

import by.agsr.monitorsensors.dto.SensorCreateDto;
import by.agsr.monitorsensors.dto.SensorDto;
import by.agsr.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sensors")
@RequiredArgsConstructor
@Validated
@Tag(name = "Sensor", description = "Sensor API")
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    @Operation(summary = "Find all")
    public ResponseEntity<List<SensorDto>> findAll(){
        return new ResponseEntity<>(sensorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name-model")
    @Operation(summary = "Find all by name or model")
    public ResponseEntity<List<SensorDto>> findAll(@RequestParam(name = "name", required = false) String name,
                                                   @RequestParam(name = "model", required = false) String model){
        return new ResponseEntity<>(sensorService.findByNameOrModel(name, model), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find by id")
    public ResponseEntity<SensorDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create sensor")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SensorDto> save(@RequestBody @Validated SensorCreateDto sensorCreateDto){
        return new ResponseEntity<>(sensorService.create(sensorCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update sensor")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SensorDto> update(@PathVariable("id") Integer id,
                                          @RequestBody @Validated SensorCreateDto sensorCreateDto){
        return new ResponseEntity<>(sensorService.update(id, sensorCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sensor")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        sensorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
