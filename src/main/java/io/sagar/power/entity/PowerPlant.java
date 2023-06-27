package io.sagar.power.entity;

import io.sagar.power.dto.PowerPlantRequestDTO;
import io.sagar.power.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POWER_PLANT")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PowerPlant extends BaseEntity {

    public PowerPlant(PowerPlantRequestDTO dto) {
        this.name = dto.getName();
        this.postcode = dto.getPostcode();
        this.capacity = dto.getCapacity();
    }


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private Integer capacity;
}
