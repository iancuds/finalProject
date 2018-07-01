package com.animal.shelter.service.impl;


import com.animal.shelter.dao.ShelterDao;
import com.animal.shelter.model.Shelter;
import com.animal.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterDao shelterDao;

    @Autowired
    public ShelterServiceImpl(ShelterDao shelterDao) {
        this.shelterDao = shelterDao;
    }


    @Override
    public List<Shelter> findAll() {
        return shelterDao.findAll();
    }

    @Override
    public Shelter findById(Long idshelter) {
        Shelter s =  shelterDao.findShelterByIdshelter(idshelter);

        return s;
    }

    @Override
    public Shelter updateCapital(Long shelterid, Float capitalAdded) {
        Shelter s = shelterDao.getOne(shelterid);
        Float c = s.getCapital();
        c=c+capitalAdded;
        s.setCapital(c);

        shelterDao.save(s);
        return s;

    }

    @Override
    public Shelter updateNrPets(Long shelterid, Long nrPetsAdded) {
        Shelter s = shelterDao.getOne(shelterid);
        Long n = s.getNrpets();
        n=n+nrPetsAdded;
        s.setNrpets(n);

        shelterDao.save(s);
        return s;

    }

    public Shelter saveShelter( Long nrpets,  Float capital,  String about)
    {
        Shelter s = new Shelter(nrpets,capital,about);
        shelterDao.save(s);

        return s;
    }

}
