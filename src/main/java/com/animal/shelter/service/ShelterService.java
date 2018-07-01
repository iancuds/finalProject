package com.animal.shelter.service;

import com.animal.shelter.model.Shelter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShelterService {

    List<Shelter> findAll();

    Shelter findById(Long shelterid);

    Shelter updateCapital(Long shelterid, Float capitalAdded);

    Shelter updateNrPets(Long shelterid, Long nrPetsAdded);

    Shelter saveShelter(Long nrpets, Float capital, String about);

}
