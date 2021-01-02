package com.example.demo.dao;

import com.example.demo.Entity.year_release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface yearReleaseRepository extends JpaRepository<year_release, Integer>,
        JpaSpecificationExecutor<year_release> {

    @Query(value = "select * from year_release " +
            "where releaseYear!=0 ORDER BY releaseYear DESC ",nativeQuery = true)
    List<year_release> findAllMovies();


//    @Override
//    List<year_release> findAll();




}
