package io.sagar.power.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerPlantResponseDTO extends PowerPlantRequestDTO {

    private UUID uuid;

    public PowerPlantResponseDTO(UUID uuid, String name, String postcode, Integer capacity) {
        super(name, postcode, capacity);
        this.uuid = uuid;
    }
}
