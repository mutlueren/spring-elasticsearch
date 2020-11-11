package com.example.springelasticsearch.repository;

import com.example.springelasticsearch.model.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    List<Person> findByName(String name);
    List<Person> findBySurname(String surname);
}
