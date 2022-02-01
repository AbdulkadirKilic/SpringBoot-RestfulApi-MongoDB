package com.example.AnimalRecording.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Size;


@JsonIgnoreProperties(value = {"secret_info","secret_number"})


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {


    @Size(min=3, max = 20, message = "Name should have atleast 3 and maximum 20 character.")
    private String name;

    private String breed;

    @Id
    private String id;

    private int age;


    private String secret_info;


    private int secret_number;









}
