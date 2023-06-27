package io.sagar.power.service.impl;

import io.sagar.power.dto.*;
import io.sagar.power.entity.PowerPlant;
import io.sagar.power.repository.PowerPlantRepository;
import io.sagar.power.service.PowerPlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PowerPlantServiceImplTest {

    @Mock
    private PowerPlantRepository powerPlantRepository;

    @InjectMocks
    private PowerPlantServiceImpl powerPlantService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addPowerPlant_WithValidInput() {
        List<PowerPlantRequestDTO> list = new ArrayList<PowerPlantRequestDTO>();
        list.add(new PowerPlantRequestDTO("Name2", "123125", 143));
        list.add(new PowerPlantRequestDTO("Name6", "1231468", 165));
        when(powerPlantRepository.saveAll(anyList())).thenReturn(anyList());
        ResponseEntity res = powerPlantService.addPowerPlant(list);
        assertTrue(res.getStatusCode().is2xxSuccessful());
    }

    @Test
    void addPowerPlant_WithInValidInput() {
        List<PowerPlantRequestDTO> list = new ArrayList<PowerPlantRequestDTO>();
        ResponseEntity res = powerPlantService.addPowerPlant(list);
        assertEquals(res.getStatusCode(), HttpStatus.BAD_REQUEST);
    }


    @Test
    void getPowerRangeByPostcode() {
        List<PowerPlant> powerPlants = getPowerPlantArray(5);
        AggregateResponseDTO aggregateResponse = getAggregateValues(powerPlants);
        when(powerPlantRepository.getPowerPlantByPostCodeRange("0001", "0002")).thenReturn(powerPlants);
        ResponseEntity<MessageResponseDTO<PowerPlantRangeResponseDTO>> res = (ResponseEntity<MessageResponseDTO<PowerPlantRangeResponseDTO>>) powerPlantService.getPowerRangeByPostcode("0001", "0002");
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertEquals(Objects.requireNonNull(res.getBody()).getData().getBatteries().size(), powerPlants.size());
        assertEquals(aggregateResponse.getAverage(), res.getBody().getData().getAggregateWattCapacity().getAverage());
        assertEquals(aggregateResponse.getMax(), res.getBody().getData().getAggregateWattCapacity().getMax());
        assertEquals(aggregateResponse.getMin(), res.getBody().getData().getAggregateWattCapacity().getMin());
        assertEquals(aggregateResponse.getTotal(), res.getBody().getData().getAggregateWattCapacity().getTotal());
    }

    @Test
    void getPowerRangeByPostcode_WIthNoData() {
        List<PowerPlant> powerPlants = getPowerPlantArray(0);
        AggregateResponseDTO aggregateResponse = getAggregateValues(powerPlants);
        when(powerPlantRepository.getPowerPlantByPostCodeRange("0001", "0002")).thenReturn(powerPlants);
        ResponseEntity<MessageResponseDTO<PowerPlantRangeResponseDTO>> res = (ResponseEntity<MessageResponseDTO<PowerPlantRangeResponseDTO>>) powerPlantService.getPowerRangeByPostcode("0001", "0002");
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertEquals(Objects.requireNonNull(res.getBody()).getData().getBatteries().size(), powerPlants.size());
        assertEquals(aggregateResponse.getAverage(), res.getBody().getData().getAggregateWattCapacity().getAverage());
        assertEquals(aggregateResponse.getMax(), res.getBody().getData().getAggregateWattCapacity().getMax());
        assertEquals(aggregateResponse.getMin(), res.getBody().getData().getAggregateWattCapacity().getMin());
        assertEquals(aggregateResponse.getTotal(), res.getBody().getData().getAggregateWattCapacity().getTotal());
    }

    private List<PowerPlant> getPowerPlantArray(int size) {
        Random random = new Random();
        List<PowerPlant> powerPlants = new ArrayList<>();
        PowerPlant powerPlant;
        for (int i = 0; i < size; i++) {
            powerPlant = new PowerPlant();
            powerPlant.setId(random.nextLong());
            powerPlant.setName("NAME" + random.nextInt());
            powerPlant.setPostcode(random.nextLong() + "");
            powerPlant.setCapacity(ThreadLocalRandom.current().nextInt(1, 9999 + 1));
            powerPlants.add(powerPlant);
        }
        return powerPlants;
    }

    private AggregateResponseDTO getAggregateValues(List<PowerPlant> powerPlants) {
        Integer total = powerPlants.stream().mapToInt(PowerPlant::getCapacity).sum();
        Integer avg = (int) powerPlants.stream().mapToInt(PowerPlant::getCapacity).average().orElse(0);
        Integer max = powerPlants.stream().mapToInt(PowerPlant::getCapacity).max().orElse(0);
        Integer min = powerPlants.stream().mapToInt(PowerPlant::getCapacity).min().orElse(0);
        return new AggregateResponseDTO(total, avg, max, min);
    }


}