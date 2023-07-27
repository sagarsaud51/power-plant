package io.sagar.power.controller;

import io.sagar.power.dto.MessageResponseDTO;
import io.sagar.power.dto.PowerPlantRangeResponseDTO;
import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.dto.PowerPlantResponseDTO;
import io.sagar.power.service.impl.PowerPlantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


class PowerControllerTest {

    @Mock
    private PowerPlantServiceImpl powerPlantService;

    @InjectMocks
    private PowerController powerController;


    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void addPowerPlant() {
        List<PowerPlantRequestDTO> list = new ArrayList<PowerPlantRequestDTO>();
        list.add(new PowerPlantRequestDTO("Name2", "123125", 654));
        list.add(new PowerPlantRequestDTO("Name6", "1234", 165));
        when(powerPlantService.addPowerPlant(list)).thenReturn(list.stream().map(l -> new PowerPlantResponseDTO(null, l.getName(), l.getPostcode(), l.getCapacity())).collect(Collectors.toList()));
        ResponseEntity<List<PowerPlantResponseDTO>> res =  powerController.addPowerPlant(list);
        assertTrue(res.getStatusCode().is2xxSuccessful());
    }


    @Test
    void getPowerRangeByPostcode() {
        PowerPlantRangeResponseDTO powerPlantRangeResponseDTO =  new PowerPlantRangeResponseDTO();
        when(powerPlantService.getPowerRangeByPostcode("001", "002")).thenReturn(powerPlantRangeResponseDTO);
        var res = powerController.getPowerRangeByPostcode("001", "002");
        assertTrue(res.getStatusCode().is2xxSuccessful());
    }
}