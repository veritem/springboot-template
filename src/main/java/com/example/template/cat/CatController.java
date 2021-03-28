package com.example.template.cat;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cats")
public class CatController {
  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
        this.catService = catService;
  }

  @GetMapping
  @ApiOperation(value = "Get all cats",notes = "Get all cats' information",response = Cat.class)
  public List<Cat> getAllCats(){
      return this.catService.getAllCats();
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Cat id")
  public void getCat(@ApiParam(value = "ID value for a cat you are looking for",required = true)
                       @PathVariable String id){
  }

  @PostMapping
  public void addCat(@RequestParam Cat cat){
    catService.addCat(cat);
  }
}
