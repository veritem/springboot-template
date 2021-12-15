package com.example.template.services;

import com.example.template.dtos.CatDTO;
import com.example.template.exceptions.ApiRequestException;
import com.example.template.exceptions.AppException;
import com.example.template.models.Cat;
import com.example.template.repository.CatRepository;
import com.example.template.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatService {
    private final CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getAllCats(){
        //Constants.validatePageNumberAndSize(page,size);
        //Pageable pageable = (Pageable) PageRequest.of(page,size, Sort.Direction.ASC,"name");
        List<Cat> cats = catRepository.findAll();
        return cats;
    }

    public Cat addCat(CatDTO catDto){
        Cat newCat = new Cat(catDto.getName());
        return catRepository.save(newCat);
    }

    public Cat getCat(UUID id) {
        Optional<Cat> isCatFound = Optional.ofNullable(catRepository.findById(id).orElseThrow(() -> new ApiRequestException("Cat with id " + id + " doesn't exists")));
        return isCatFound.get();
    }

    public ResponseEntity<?> deleteCat(UUID id) {
        Optional<Cat> isCatFound = Optional.ofNullable(catRepository.findById(id).orElseThrow(() -> new ApiRequestException("Cat with id " + id + " doesn't exists")));
        catRepository.delete(isCatFound.get());
        return ResponseEntity.ok().build();
    }
}
