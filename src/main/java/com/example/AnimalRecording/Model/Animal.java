package com.example.AnimalRecording.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    private String name, breed;

    @Id
    private String id;

    private int age;









}
