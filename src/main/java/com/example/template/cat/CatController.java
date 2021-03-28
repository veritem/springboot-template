package com.example.template.cat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cats")
public class CatController {
  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
        this.catService = catService;
  }

  @GetMapping
  public List<Cat> getAllCats(){
      return this.catService.getAllCats();
  }

  @PostMapping
  public void addCat(@RequestParam Cat cat){
    catService.addCat(cat);
  }
}
