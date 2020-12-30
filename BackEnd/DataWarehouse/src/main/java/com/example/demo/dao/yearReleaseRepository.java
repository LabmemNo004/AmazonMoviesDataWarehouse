package com.example.demo.dao;

import com.example.demo.Entity.year_release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface yearReleaseRepository extends JpaRepository<year_release, Integer>,
        JpaSpecificationExecutor<year_release> {
    @Override
    List<year_release> findAll();




}
