package org.example;

import org.example.dao.entity.Pet;
import org.example.service.PetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private PetService petService;

    @Test
    public void testGetAll() {
        List<Pet> pets = petService.getAll();
        pets.stream().forEach(System.out::println);
    }

    @Test
    public void testWrite() {
        Pet pet = new Pet();
        pet.setId(100000008l);
        pet.setName("riana");
        pet.setOwner("alex");
        pet.setSex("F");
        pet.setSpecies("bunny");
        pet.setAge(26);
        petService.save(pet);
    }
}
