package com.example.AnimalRecording.Controller;

import com.example.AnimalRecording.Model.Animal;
import com.example.AnimalRecording.Service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
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
    public List<Animal> getAnimals() {

        List<Animal> animals = animalService.getAnimals();

        return animals;



    }


    @GetMapping("/{id}")
    public EntityModel<Animal> getAnimal(@PathVariable("id") String animalId) {
        animalService.notFoundExceptionControl(animalId);
        Animal animal =animalService.getAnimalById(animalId);

        EntityModel<Animal> model = EntityModel.of(animal);

        WebMvcLinkBuilder linkToAnimals = linkTo(methodOn(this.getClass()).getAnimals());
        model.add(linkToAnimals.withRel("All Animals"));
        return model;

    }


    @PostMapping
    public ResponseEntity<Animal> post(@Valid @RequestBody Animal newAnimal) {
        Animal savedAnimal = animalService.create(newAnimal);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAnimal.getId()).toUri();

        return ResponseEntity.created(location).build();
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

