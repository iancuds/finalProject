package com.animal.shelter.dao;

import com.animal.shelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShelterDao extends JpaRepository<Shelter, Long> {

    Shelter findShelterByIdshelter(Long idshelter);
}
