package com.animal.shelter.controller;


import com.animal.shelter.model.Shelter;
import com.animal.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shelter")
public class ShelterController {

    private final ShelterService shelterService;

    @Autowired
    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }


    public List<Shelter> findAll()
    {
        return shelterService.findAll();
    }

    public Shelter findById(@RequestParam Long shelterid)
    {
        return shelterService.findById(shelterid);
    }

    public Shelter updateCapital(@RequestParam Long shelterid, @RequestParam Float capitalAdded)
    {
        return shelterService.updateCapital(shelterid,capitalAdded);
    }

    public Shelter updateNrPets(@RequestParam Long shelterid, @RequestParam Long nrPetsAdded)
    {
        return shelterService.updateNrPets(shelterid,nrPetsAdded);
    }

    public Shelter saveShelter(@RequestParam Long nrpets, @RequestParam Float capital, @RequestParam String about)
    {
        return shelterService.saveShelter(nrpets,capital,about);
    }
}
