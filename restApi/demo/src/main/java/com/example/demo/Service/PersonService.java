package com.example.demo.Service;


import com.example.demo.Exceptions.IdNotFoundException;
import com.example.demo.Repository.PersonRepository;


import com.example.demo.User.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@Service
public class PersonService {
    @Autowired
    public  PersonRepository personRepository;

    public void ServiceSavemethod(Person user) {

        personRepository.save(user);
    }

    public ResponseEntity<Person> Servicefindmethod(Long id) throws IdNotFoundException {

        Optional<Person> user = personRepository.findById(id);
        if (user.isPresent()) {
            Person actualuser = user.get();
            return ResponseEntity.ok().header("output", "object information").body(actualuser);
        }

        throw new IdNotFoundException("id not found in database, please try again");


    }


    //ResponseEntity<Map<String,Object>>
    public List<Long> servicefindagemethod(int age) {
        List<Person> li = personRepository.findAll();


        Function<Person, Long> lambVar = person -> person.getId();
        List<Long> info = li.stream().filter(person -> person.age < 30).map(lambVar).toList();


        return info;

    }


    public String deleteid(Long id) {

        if (personRepository.existsById(id))
            return "not found";
        return null;

    }


    public String ServiceAgeCheck(Long age){
        List<Person> li=personRepository.findAll();
        if(li.isEmpty()){
            return null;
        }

        //Function<Person, Long> lambVar = person -> ageLi.add(person.age);//if dont do this then the first and second in "Function"argument is whatever is automatically passed in and the reference type is "Function<...,...>"

        List<Integer> ageLi =new ArrayList<>();
        li.stream().filter(person->person.getAge()<=age).forEach(person->ageLi.add(person.getAge()));

        return (ageLi.size()*100)/ li.size()+"% of the people are under the age of "+age;


    }







}
