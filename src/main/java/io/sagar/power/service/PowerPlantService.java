package io.sagar.power.service;

import io.sagar.power.dto.PowerPlantRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PowerPlantService {


    ResponseEntity<?> addPowerPlant(List<PowerPlantRequestDTO> requestDTOS);

    ResponseEntity<?> getPowerRangeByPostcode(String fromPostcode, String toPostcode);
}
