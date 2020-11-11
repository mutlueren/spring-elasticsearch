package com.example.springelasticsearch.service;

import com.example.springelasticsearch.model.Person;
import com.example.springelasticsearch.repository.PersonRepository;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    /* date of birth converter */
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /* demo data test */
    public void generateData(){
        Person p = new Person();
        p.setId("PME1995");
        p.setName("Mutlu");
        p.setSurname("Eren");
        p.setBirthDate(parseDate("1995-03-02"));
    }

    /* get all persons */
    public Iterable<Person> getAllPersons(){
        return personRepository.findAll();
    }

    /* find persons by name */
    public List<Person> findPersonByName(String name){
        return personRepository.findByName(name);
    }

    /* find persons by surname */
    public List<Person> findPersonBySurname(String surname){
        return personRepository.findBySurname(surname);
    }

    /* create new person */
    public Person createNewPerson(Person person){
        return personRepository.save(person);
    }

    /* update a person */
    public ResponseEntity<Person> updateEmployee(String id, Person personDetails) throws ResourceNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :" + id));

        person.setName(personDetails.getName());
        person.setSurname(personDetails.getSurname());
        person.setBirthDate(personDetails.getBirthDate());

        final Person updatedPerson = personRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    /* delete a person by id */
    public boolean deletePersonById(String id) {
        try {
            personRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("Error catched: "+e.getMessage());
            return false;
        }
    }

}
