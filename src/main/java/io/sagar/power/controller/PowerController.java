package io.sagar.power.controller;


import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.service.PowerPlantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> addPowerPlant(@RequestBody @Valid List<PowerPlantRequestDTO> requestDTOS) {
        return powerPlantServiceImpl.addPowerPlant(requestDTOS);
    }

    @GetMapping
    public ResponseEntity<?> getPowerRangeByPostcode(@RequestParam(required = true) String fromPostcode, @RequestParam(required = true) String toPostcode) {
        return powerPlantServiceImpl.getPowerRangeByPostcode(fromPostcode, toPostcode);
    }

}
