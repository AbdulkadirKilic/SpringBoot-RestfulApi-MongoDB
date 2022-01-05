package com.example.AnimalRecording.Service;

import com.example.AnimalRecording.Model.Animal;
import com.example.AnimalRecording.Repository.IAnimalRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalService  {
    private final IAnimalRepository repository;

    public List<Animal> getAnimals(){
        return repository.findAll();
    }

    public Animal getAnimalById(String id){

        Optional<Animal> optionalAnimal=repository.findById(id);

        if(optionalAnimal.isEmpty()){

            throw new RuntimeException();
        }
        return optionalAnimal.get();

    }

    public Animal create(Animal newAnimal){

        return repository.save(newAnimal);
    }

    public void put(Animal newAnimal,String id){
        Animal oldAnimal = getAnimalById(id);

        oldAnimal.setName(newAnimal.getName());
        oldAnimal.setBreed(newAnimal.getBreed());
        oldAnimal.setAge(newAnimal.getAge());
        repository.save(oldAnimal);

    }

    public void delete(String id){
        repository.deleteById(id);

    }



}
