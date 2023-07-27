package io.sagar.power.controller;


import io.sagar.power.dto.PowerPlantRangeResponseDTO;
import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.dto.PowerPlantResponseDTO;
import io.sagar.power.service.PowerPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/power")
@Validated
public class PowerController {


    @Autowired
    private PowerPlantService powerPlantServiceImpl;


    @PostMapping
    public ResponseEntity<List<PowerPlantResponseDTO>> addPowerPlant(@RequestBody @Valid List<PowerPlantRequestDTO> requestDTOS) {
        return ResponseEntity.status(HttpStatus.CREATED).body(powerPlantServiceImpl.addPowerPlant(requestDTOS));
    }

    @GetMapping
    public ResponseEntity<PowerPlantRangeResponseDTO> getPowerRangeByPostcode(@RequestParam(required = true) String fromPostcode, @RequestParam(required = true) String toPostcode) {
        return ResponseEntity.ok(powerPlantServiceImpl.getPowerRangeByPostcode(fromPostcode, toPostcode));
    }

}
