package com.example.demo.dao;

import com.example.demo.Entity.release_time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface releaseTimeRepository extends JpaRepository<release_time, Integer>,
        JpaSpecificationExecutor<release_time> {

    List<release_time> findByReleaseYearAndReleaseMonthAndReleaseDay(
            Integer year, Integer month, Integer day);

}
