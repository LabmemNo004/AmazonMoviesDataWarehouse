package com.example.demo.dao;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Entity.type;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface typeRepository extends JpaRepository<type, String>,
        JpaSpecificationExecutor<type> {

    @Query(value = "select m.title,m.score from type s join product p on s.type = p.type " +
            " join movie m on m.movieID = p.movieID " +
            " WHERE p.type=?1",nativeQuery = true)
    List<Map<String,Float>> getMovieByType(String type);

    List<type> findByMovieNumGreaterThanOrderByMovieNumDesc(Integer index);

    List<type> findByMovieNumGreaterThanOrderByMovieNumAsc(Integer index);

    @Query(value = "select movie.title,movie.score from movie " +
            "where movie.score>=?1 ORDER BY movie.score DESC ",nativeQuery = true)
    List<Map<String,String>> getGreaterScoreMovie(Integer scores);


    @Query(value="select movie.title,movie.score from movie " +
            "where movie.hadPositiveComment='T' ORDER BY movie.score DESC ",nativeQuery = true)
    List<Map<String,String>> getPositiveMovie();

    @Query(value="with some as ( select * from year_type_num t where " +
            "t.year=?1) " +
            "select s.type,some.movieNum from type s join some on s.type = some.type " +
            "order by some.movieNum DESC",nativeQuery = true)
    List<Map<String,Integer>> getTimeTypePopular(Integer time);


}
