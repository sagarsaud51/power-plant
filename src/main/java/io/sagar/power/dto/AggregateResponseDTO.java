package io.sagar.power.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateResponseDTO {

    private Integer total;
    private Integer average;
    private Integer max;
    private Integer min;
}
