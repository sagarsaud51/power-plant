package io.sagar.power.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerPlantRequestDTO {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, message = "Minimum length for name should be 1")
    private String name;

    @NotNull(message = "postcode cannot be null")
    @NotBlank(message = "postcode cannot be blank")
    @Pattern(regexp = "^[0-9]*$", message = "Only numeric value for postcode is allowed")
    private String postcode;

    @Min(value = 1, message = "capacity should have Only positive and greater than 0 value")
    private Integer capacity;
}
