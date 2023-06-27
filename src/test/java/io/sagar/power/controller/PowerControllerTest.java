package io.sagar.power.controller;

import io.sagar.power.dto.MessageResponseDTO;
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
        ResponseEntity r = new ResponseEntity(new MessageResponseDTO<>(true, "succes", null), HttpStatus.CREATED);
        when(powerPlantService.addPowerPlant(list)).thenReturn(r);
        ResponseEntity<MessageResponseDTO<List<PowerPlantResponseDTO>>> res = (ResponseEntity<MessageResponseDTO<List<PowerPlantResponseDTO>>>) powerController.addPowerPlant(list);
        assertTrue(res.getStatusCode().is2xxSuccessful());
    }

    @Test
    void addPowerPlant_withEmptyArray() {
        List<PowerPlantRequestDTO> list = new ArrayList<PowerPlantRequestDTO>();
        ResponseEntity r = new ResponseEntity(new MessageResponseDTO<>(false, "Failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        when(powerPlantService.addPowerPlant(list)).thenReturn(r);
        ResponseEntity<MessageResponseDTO<List<PowerPlantResponseDTO>>> res = (ResponseEntity<MessageResponseDTO<List<PowerPlantResponseDTO>>>) powerController.addPowerPlant(list);
        assertTrue(!res.getStatusCode().is2xxSuccessful());
    }

    @Test
    void getPowerRangeByPostcode() {
        ResponseEntity r = new ResponseEntity(new MessageResponseDTO<>(true, "Success", null), HttpStatus.OK);
        when(powerPlantService.getPowerRangeByPostcode("001","002")).thenReturn(r);
        var res  = powerController.getPowerRangeByPostcode("001", "002");
        assertTrue(res.getStatusCode().is2xxSuccessful());
    }
}