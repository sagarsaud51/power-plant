package io.sagar.power.service;

import io.sagar.power.dto.PowerPlantRangeResponseDTO;
import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.dto.PowerPlantResponseDTO;

import java.util.List;

public interface PowerPlantService {


    List<PowerPlantResponseDTO> addPowerPlant(List<PowerPlantRequestDTO> requestDTOS);

    PowerPlantRangeResponseDTO getPowerRangeByPostcode(String fromPostcode, String toPostcode);
}
