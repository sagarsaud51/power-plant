package io.sagar.power.repository;


import io.sagar.power.entity.PowerPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerPlantRepository extends JpaRepository<PowerPlant, Long> {


    @Query("SELECT ENTITY from PowerPlant ENTITY where CAST(ENTITY.postcode AS integer) >=  CAST(:fromPostcode AS integer) and CAST(ENTITY.postcode AS integer) <= CAST(:toPostcode AS integer)")
    List<PowerPlant> getPowerPlantByPostCodeRange(@Param("fromPostcode") String fromPostcode, @Param("toPostcode") String toPostcode);

    @Query("SELECT ENTITY.id from PowerPlant ENTITY where ENTITY.postcode in(:ids)")
    List<String> getPowerPlantsIds(@Param("ids") List<String> ids);
}
