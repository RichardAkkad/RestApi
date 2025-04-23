package com.example.demo.Controller;


import com.example.demo.Exceptions.IdNotFoundException;
import com.example.demo.Service.PersonService;
import com.example.demo.User.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {
    @Autowired
    PersonService personService;



    @GetMapping("save")
    public void  SaveMethod(@RequestBody Person user) {
        personService.ServiceSavemethod(user);


    }

    @GetMapping("searchById")
    public ResponseEntity<Person> findPersonMethod(@RequestParam Long id)  {
        try {
            return personService.Servicefindmethod(id);
        }
        catch(Exception e){

            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();// need this if want details about the exception in the terminal because cant return and send a exception to the terminal


            return ResponseEntity.notFound().build();


        }

    }



    @GetMapping("ageUnder30")
    public List<Long> searchForSpecificAge(@RequestParam int age ){
        return personService.servicefindagemethod(age);


    }

    @PostMapping("deleteId")
    public String deleteId(@RequestParam Long id){
        return personService.deleteid(id);


    }

    @GetMapping("underAgePercentage")
    public String returnPercentage(@RequestParam Long age){
        return personService.ServiceAgeCheck(age);

    }














}
