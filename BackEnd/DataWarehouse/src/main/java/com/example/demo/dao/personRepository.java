package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.person;

import java.util.List;

public interface personRepository extends JpaRepository<person, Integer>{

    List<person> findAll();



}
