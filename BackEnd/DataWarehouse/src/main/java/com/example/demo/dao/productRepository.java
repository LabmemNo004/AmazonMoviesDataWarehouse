package com.example.demo.dao;


import com.example.demo.Entity.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface productRepository extends JpaRepository<product, Integer>,
        JpaSpecificationExecutor<product> {

    List<product> findByTimeIDIn(List<Integer> temp);

    List<product> findByMovieIDInOrderByTimeIDDesc(List<Integer> temp);
}
