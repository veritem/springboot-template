package com.example.template.cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    private final CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getAllCats(){
        return this.catRepository.findAll();
    }

    public void addCat(Cat cat){
        catRepository.save(cat);
    }
}
