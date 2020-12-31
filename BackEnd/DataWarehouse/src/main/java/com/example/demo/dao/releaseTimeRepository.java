package com.example.demo.dao;

import com.example.demo.Entity.release_time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface releaseTimeRepository extends JpaRepository<release_time, Integer>,
        JpaSpecificationExecutor<release_time> {

    List<release_time> findByReleaseYearAndReleaseMonthAndReleaseDay(
            Integer year, Integer month, Integer day);


    release_time findByTimeID(Integer temp);

    @Query(value = "with thisDirector as (" +
            "    select s.personID from person s" +
            "    where s.name=?2)," +
            " TypeTimeMovie as (" +
            "    select productID,releaseYear,type,movieID," +
            "           product.timeID as time" +
            "    from product join" +
            "        release_time rt on rt.timeID = product.timeID" +
            "    where rt.releaseYear=?1 AND product.type=?3" +
            ")" +
            "select DISTINCT m.title,m.score from thisDirector tD join relation r" +
            "    on tD.personID=r.personID join movie m on m.movieID = r.movieID" +
            "    join TypeTimeMovie TT on TT.movieID=m.movieID" +
            "    join person p on r.personID = p.personID" +
            "    ORDER BY m.score DESC;",nativeQuery = true)
    List<Map<String,Float>>getTimeDirectorType(Integer year,String name,String type);

}
