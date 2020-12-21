package com.example.demo.dao;

import com.example.demo.Entity.release_time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface releaseTimeRepository extends JpaRepository<release_time, Integer>,
        JpaSpecificationExecutor<release_time> {


    @Query(value="select u.timeID from release_time u " +
            "where u.releaseYear=?1 and u.releaseMonth=?2 and u.releaseDay =?3",
            nativeQuery = true)
    List<Integer> gettimeIDByYMD(Integer year, Integer month, Integer day);


    List<release_time> findByReleaseYearAndReleaseMonthAndReleaseDay(
            Integer year, Integer month, Integer day);


}
