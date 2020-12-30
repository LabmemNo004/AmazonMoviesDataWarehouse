package com.example.demo.dao;


import com.alibaba.fastjson.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.person;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface personRepository extends JpaRepository<person, Integer>{

    List<person> findAll();

    @Query(value = "select m.title,m.score from person s join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isDirector='T' AND s.name=?1",nativeQuery = true)
    JSONArray getMoviesByDirector(String name);

    List<person> findByDirectorNumGreaterThanEqualAndActorEitherDirectorOrderByDirectorNumDesc(
            Integer number,Character actorOrDirector);


    @Query(value = "select m.title,m.score from person s join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isActor='T' AND s.name=?1",nativeQuery = true)
    List<Map<String,Float>> getMoviesByActor(String name);

    List<person> findByActorNumGreaterThanEqualAndActorEitherDirectorOrderByActorNumDesc(
            Integer number,Character actorOrDirector);


    List<person> findByPersonIDIn(List<Integer> number);

    /**
     * 理解错误，假想设计，废~~
     * "select count(DISTINCT m.movieID) as numberIndex from person s join relation c on s.personID = c.personID" +
     * " join movie m on c.movieID = m.movieID" +
     * " where c.isDirector='T' GROUP BY s.personID"
     */

}
