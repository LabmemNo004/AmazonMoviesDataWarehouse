package com.example.demo.dao;

import com.example.demo.Entity.movie;
import com.example.demo.Entity.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface productRepository extends JpaRepository<product, Integer>,
        JpaSpecificationExecutor<product> {

    @Query(value = "select u.movieID from product u " +
            "where u.timeID in ?1")
    List<Integer> getmovieIDBy(List<Integer> timeID);

    List<product> findByTimeIDIn(List<Integer> temp);
}
