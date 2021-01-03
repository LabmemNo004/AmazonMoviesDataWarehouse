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
            " where c.isDirector='T' AND s.name=?1 " +
            " order by m.score DESC",nativeQuery = true)
    List<Map<String,Float>> getMoviesByDirector(String name);

    @Query(value = "select m.title,m.score,s.name from person s " +
            "join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isDirector='T' AND s.name like concat(?1,'%') " +
            " order by m.score DESC",nativeQuery = true)
    List<Object []> getMoviesByDirectorLike(String name);


    @Query(value = "select m.title,m.score from person s join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isActor='T' AND s.name=?1" +
            " ORDER BY m.score DESC",nativeQuery = true)
    List<Map<String,Float>> getMoviesByActor(String name);


    @Query(value = "select m.title,m.score,s.name " +
            " from person s join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isActor='T' AND s.name like concat(?1,'%')" +
            " ORDER BY m.score DESC",nativeQuery = true)
    List<Object []> getMoviesByActorLike(String name);



    @Query(value="select * from person where person.directorNum>=?1 " +
            "AND person.actorEitherDirector=?2 ORDER BY person.directorNum DESC"
            ,nativeQuery = true)
    List<person> getGreaterDirector(Integer number,Character actorOrDirector);

    @Query(value="select * from person where person.actorNum>=?1 " +
            "AND person.actorEitherDirector=?2 ORDER BY person.actorNum DESC",nativeQuery = true)
    List<person> getGreaterActor(Integer number,Character actorOrDirector);


    List<person> findByPersonIDIn(List<Integer> number);

    /**
     * 理解错误，假想设计，废~~
     * "select count(DISTINCT m.movieID) as numberIndex from person s join relation c on s.personID = c.personID" +
     * " join movie m on c.movieID = m.movieID" +
     * " where c.isDirector='T' GROUP BY s.personID"
     */

}
