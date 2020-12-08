package org.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.annotation.Master;
import org.example.dao.PetDao;
import org.example.dao.entity.Pet;
import org.example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetDao petDao;

    @Transactional
    @Override
    public int save(Pet pet) {
        return petDao.insert(pet);
    }

    //@Master
    @Override
    public List<Pet> getAll() {
        return petDao.selectList(Wrappers.emptyWrapper());
    }
}
