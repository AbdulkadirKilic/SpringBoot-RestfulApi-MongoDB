package com.example.AnimalRecording.Controller;

import com.example.AnimalRecording.Model.Animal;
import com.example.AnimalRecording.Service.AnimalService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/animals")
@AllArgsConstructor
public class Controller {

    private final AnimalService animalService;


    @GetMapping()
    public MappingJacksonValue getAnimals() {

        List<Animal> animals = animalService.getAnimals();

        //Dynamic Filter
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("name","breed","id","age");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Filtered Bean",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(animals);
        mapping.setFilters(filters);

        return mapping;

    }


    @GetMapping("/{id}")
    public MappingJacksonValue getAnimal(@PathVariable("id") String animalId) {
        animalService.notFoundExceptionControl(animalId);
        Animal animal =animalService.getAnimalById(animalId);

        EntityModel<Animal> model = EntityModel.of(animal);

        WebMvcLinkBuilder linkToAnimals = linkTo(methodOn(this.getClass()).getAnimals());
        model.add(linkToAnimals.withRel("All Animals"));

        //Dynamic Filter
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("name","breed","id","age");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Filtered Bean",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(animal);
        mapping.setFilters(filters);

        return mapping;

    }


    @PostMapping
    public ResponseEntity<Animal> post(@Valid @RequestBody Animal newAnimal) {
        Animal savedAnimal = animalService.create(newAnimal);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAnimal.getId()).toUri();

        return ResponseEntity.created(location).build();  //Create source location in header
    }


    @PutMapping("/{id}")
    public void update (@PathVariable("id") String animalId, @RequestBody Animal newAnimal ){
        animalService.notFoundExceptionControl(animalId);

        animalService.put(newAnimal, animalId);
    }


    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") String id){
        animalService.notFoundExceptionControl(id);

        animalService.delete(id);

    }


}

