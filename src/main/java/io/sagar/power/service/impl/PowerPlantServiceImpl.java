package io.sagar.power.service.impl;

import io.sagar.power.dto.AggregateResponseDTO;
import io.sagar.power.dto.PowerPlantRangeResponseDTO;
import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.dto.PowerPlantResponseDTO;
import io.sagar.power.entity.PowerPlant;
import io.sagar.power.repository.PowerPlantRepository;
import io.sagar.power.service.PowerPlantService;
import io.sagar.power.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PowerPlantServiceImpl implements PowerPlantService {

    private final Logger logger = LoggerFactory.getLogger(PowerPlantServiceImpl.class);


    @Autowired
    private PowerPlantRepository powerPlantRepository;


    @Override
    public ResponseEntity<?> addPowerPlant(List<PowerPlantRequestDTO> requestDTOS) {
        try {
            //  todo need to verify if different city can have same postcode

//            List<String> powerPlantsIds = powerPlantRepository.getPowerPlantsIds(requestDTOS.stream().map(PowerPlantRequestDTO::getPostcode).collect(Collectors.toList()));
//            if (!powerPlantsIds.isEmpty()) {
//                throw new PowerException("postcode [" + StringUtils.join(powerPlantsIds, ',') + "] already exists in the system");
//            }
            List<PowerPlant> list = requestDTOS.stream().map(PowerPlant::new).collect(Collectors.toList());
            powerPlantRepository.saveAll(list);
            return ResponseUtils.responseGenerator(list.stream().map(l -> new PowerPlantResponseDTO(l.getUuid(), l.getName(), l.getPostcode(), l.getCapacity())).collect(Collectors.toList()), true, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while inserting power plant " + e.getMessage());
            return ResponseUtils.responseGenerator(Map.of("error", e.getMessage()), false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getPowerRangeByPostcode(String fromPostcode, String toPostcode) {
        try {
            List<PowerPlant> powerPlants = powerPlantRepository.getPowerPlantByPostCodeRange(fromPostcode, toPostcode);
            AggregateResponseDTO aggregateResponseDTO = getAggregateValues(powerPlants);
            PowerPlantRangeResponseDTO powerPlantRangeResponseDTO = new PowerPlantRangeResponseDTO();
            powerPlantRangeResponseDTO.setAggregateWattCapacity(aggregateResponseDTO);
            // sort array
            powerPlantRangeResponseDTO.setBatteries(powerPlants.stream().map(PowerPlant::getName).sorted().collect(Collectors.toList()));
            return ResponseUtils.responseGenerator(powerPlantRangeResponseDTO, true, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error cannot fetch powerPlant range with cause " + e.getMessage());
            return ResponseUtils.responseGenerator(Map.of("error", e.getMessage()), false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //aggregate of power plants
    private AggregateResponseDTO getAggregateValues(List<PowerPlant> powerPlants) {
        Integer total = powerPlants.stream().mapToInt(PowerPlant::getCapacity).sum();
        Integer avg = (int) powerPlants.stream().mapToInt(PowerPlant::getCapacity).average().orElse(0);
        Integer max = powerPlants.stream().mapToInt(PowerPlant::getCapacity).max().orElse(0);
        Integer min = powerPlants.stream().mapToInt(PowerPlant::getCapacity).min().orElse(0);
        return new AggregateResponseDTO(total, avg, max, min);
    }
}
