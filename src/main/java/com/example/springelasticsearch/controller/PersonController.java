package com.example.springelasticsearch.controller;

import com.example.springelasticsearch.model.Person;
import com.example.springelasticsearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/generate")
    public String generateData(){
        try {
            personService.generateData();
            return "Successfully generated.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAllPersons")
    public Iterable<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping("/findPersonByName/{name}")
    public List<Person> findPersonByName(@PathVariable String name) {
        return personService.findPersonByName(name);
    }

    @GetMapping("/findPersonBySurname/{surname}")
    public List<Person> findPersonBySurname(@PathVariable String surname) {
        return personService.findPersonBySurname(surname);
    }

    @PostMapping("/savePerson")
    public Person saveCustomer(@RequestBody Person person) {
        return personService.createNewPerson(person);
    }

    @PutMapping("/updatePerson")
    public Person updateUser(@RequestBody Person person) {
        return personService.createNewPerson(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updateEmployee(@PathVariable(value = "id") String id, @RequestBody Person personDetails){
        return personService.updateEmployee(id, personDetails);
    }

    @DeleteMapping("/deletePersonById")
    public boolean deletePersonById(@RequestParam String id){
        return personService.deletePersonById(id);
    }

}
