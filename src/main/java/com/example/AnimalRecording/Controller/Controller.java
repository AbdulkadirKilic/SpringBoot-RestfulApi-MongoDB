package com.example.AnimalRecording.Controller;

import com.example.AnimalRecording.Model.Animal;
import com.example.AnimalRecording.Exception.AnimalNotFoundException;
import com.example.AnimalRecording.Service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/animals")
@AllArgsConstructor
public class Controller {

    private final AnimalService animalService;


    @GetMapping()
    public List<Animal> getAnimals() {

        return animalService.getAnimals();

    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") String animalId) {
        Animal responseAnimal =animalService.getAnimalById(animalId);

        animalService.notFoundExceptionControl(animalId);

        return responseAnimal;


    }

    @PostMapping
    public ResponseEntity<Animal> post(@RequestBody Animal newAnimal) {
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

