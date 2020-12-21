package com.example.demo.dao;

import com.example.demo.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface movieRepository extends JpaRepository<movie, Integer>,
        JpaSpecificationExecutor<movie> {

    List<movie> findByMovieIDIn(List<Integer> temp);

}
