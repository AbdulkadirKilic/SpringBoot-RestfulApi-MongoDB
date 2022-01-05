package com.example.AnimalRecording.Repository;


import com.example.AnimalRecording.Model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAnimalRepository extends MongoRepository<Animal,String> {
}
