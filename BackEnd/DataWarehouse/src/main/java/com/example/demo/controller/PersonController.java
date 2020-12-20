package com.example.demo.controller;

import com.example.demo.Entity.person;
import com.example.demo.dao.personRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//使用该注解返回类型自动调整为json.
@RequestMapping("/person")
public class PersonController {

    @Autowired
    personRepository personRepository;

    @GetMapping(value = "/watchList")
    public List<person> getWatchList()
    {
        return personRepository.findAll();
    }


    @GetMapping(value="test")
    public List<String> test()
    {
        Integer[] array = { 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16 };
        List<Integer> result=new ArrayList<>();

        List<Integer> a= Arrays.asList(array);

        List<String>positive=new ArrayList<String>();
        List<String>negative=new ArrayList<String>();

        for(int x:a)
        {
            if(x<16)
            {
                result.add(x + 1);
                result.add(x + 16);
                result.add(x + 17);
            }
            else
            {
                break;
            }
        }
        result.add(16);
        result.add(32);
        for(Integer x:result)
        {
            if(x<=16)
            {
                positive.add("T");
                negative.add("F");
            }
            else
            {
                positive.add("F");
                negative.add("T");
            }
        }


        List<String> strings = result.stream().map(x -> x + "").collect(Collectors.toList());
        return strings;

    }
}
