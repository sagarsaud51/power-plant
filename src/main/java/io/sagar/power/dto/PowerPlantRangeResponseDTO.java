package io.sagar.power.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerPlantRangeResponseDTO {

    private List<String> batteries;
    private AggregateResponseDTO aggregateWattCapacity;

}
