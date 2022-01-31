package com.example.AnimalRecording.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Size;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {


    @Size(min=3, message = "Name should have atleast 3 character.")
    private String name;

    private String breed;

    @Id
    private String id;

    private int age;









}
