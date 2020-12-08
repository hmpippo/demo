package org.example.service;

import org.example.dao.entity.Pet;
import java.util.List;

public interface PetService {

    public int save(Pet pet);

    public List<Pet> getAll();
}
