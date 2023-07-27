package io.sagar.power.service.impl;

import io.sagar.power.dto.AggregateResponseDTO;
import io.sagar.power.dto.PowerPlantRangeResponseDTO;
import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.dto.PowerPlantResponseDTO;
import io.sagar.power.entity.PowerPlant;
import io.sagar.power.exceptions.PowerException;
import io.sagar.power.repository.PowerPlantRepository;
import io.sagar.power.service.PowerPlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PowerPlantServiceImpl implements PowerPlantService {

    private final Logger logger = LoggerFactory.getLogger(PowerPlantServiceImpl.class);


    @Autowired
    private PowerPlantRepository powerPlantRepository;


    // to add power plant data in db
    @Override
    public List<PowerPlantResponseDTO> addPowerPlant(List<PowerPlantRequestDTO> requestDTOS) {
        if (requestDTOS.isEmpty()) {
            throw new PowerException("Invalid Request");
        }
        List<PowerPlant> list = requestDTOS.stream().map(PowerPlant::new).collect(Collectors.toList());
        powerPlantRepository.saveAll(list);
        return list.stream().map(l -> new PowerPlantResponseDTO(l.getUuid(), l.getName(), l.getPostcode(), l.getCapacity())).collect(Collectors.toList());
    }

    // Search Power Plant by postcode range
    @Override
    public PowerPlantRangeResponseDTO getPowerRangeByPostcode(String fromPostcode, String toPostcode) {
        List<PowerPlant> powerPlants = powerPlantRepository.getPowerPlantByPostCodeRange(fromPostcode, toPostcode);
        AggregateResponseDTO aggregateResponseDTO = getAggregateValues(powerPlants);
        PowerPlantRangeResponseDTO powerPlantRangeResponseDTO = new PowerPlantRangeResponseDTO();
        powerPlantRangeResponseDTO.setAggregateWattCapacity(aggregateResponseDTO);
        // sort array
        powerPlantRangeResponseDTO.setBatteries(powerPlants.stream().map(PowerPlant::getName).sorted().collect(Collectors.toList()));
        return powerPlantRangeResponseDTO;
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
