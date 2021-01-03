package com.example.demo.dao;

import com.example.demo.Entity.relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface relationRepository extends JpaRepository<relation, Integer>,
        JpaSpecificationExecutor<relation> {

    @Query(value="select p.name from movie q join relation r on q.movieID = r.movieID " +
            "join person p on p.personID = r.personID " +
            "where q.title=?1 AND r.isDirector=?2 ORDER BY p.name ASC ",nativeQuery = true)
    List<String> getAssociateDirector(String title,Character identify);

    @Query(value="select p.name,q.title from movie q join relation r on q.movieID = r.movieID " +
            "join person p on p.personID = r.personID " +
            "where q.title like concat(?1,'%') AND r.isDirector=?2 ORDER BY p.name ASC",
            nativeQuery = true)
    List<Map<String,String>> getAssociateDirectorLike(String title, Character identify);

    @Query(value="select p.name from movie q join relation r on q.movieID = r.movieID " +
            "join person p on p.personID = r.personID " +
            "where q.title=?1 AND r.isActor=?2 ORDER BY p.name ASC",nativeQuery = true)
    List<String> getAssociateActor(String title,Character identify);

    @Query(value="select p.name,q.title from movie q join relation r on q.movieID = r.movieID " +
            "join person p on p.personID = r.personID " +
            "where q.title like concat(?1,'%') AND r.isActor=?2 ORDER BY p.name ASC",
            nativeQuery = true)
    List<Map<String,String>> getAssociateActorLike(String title, Character identify);



}
