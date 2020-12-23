package com.example.demo.dao;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface personRepository extends JpaRepository<person, Integer>{

    List<person> findAll();

    @Query(value = "select m.title,m.score from person join relation c on person.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isDirector='T'",nativeQuery = true)
    List<Map<String,Float>> getMoviesByDirector(String name);

    List<person> findByDirectorNumGreaterThanEqualOrderByDirectorNumDesc(Integer number);

    @Query(value = "select m.title,m.score from person join relation c on person.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isActor='T'",nativeQuery = true)
    List<Map<String,Float>> getMoviesByActor(String name);

    List<person> findByActorNumGreaterThanEqualOrderByActorNumDesc(Integer number);


    /**
     * 理解错误，假想设计，废~~
     * "select count(DISTINCT m.movieID) as numberIndex from person s join relation c on s.personID = c.personID" +
     * " join movie m on c.movieID = m.movieID" +
     * " where c.isDirector='T' GROUP BY s.personID"
     */

}
