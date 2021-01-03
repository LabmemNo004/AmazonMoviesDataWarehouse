package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.Type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TypeDAO extends BaseMapper<Type> {
    @Select(value = "select m.title,m.score from type s join product p on s.type = p.type " +
            " join movie m on m.movieID = p.movieID " +
            " WHERE p.type=#{param1}")
    List<Map<String,Float>> getMovieByType(@Param("param1") String type);

    @Select("select type as type, movieNum as movieNum" +
            " from type" +
            " where movieNum>#{param1}" +
            " order by movieNum desc")
    List<Type> findByMovieNumGreaterThanOrderByMovieNumDesc(@Param("param1") Integer index);

    @Select("select type as type, movieNum as movieNum" +
            " from type" +
            " where movieNum>#{param1}" +
            " order by movieNum asc")
    List<Type> findByMovieNumGreaterThanOrderByMovieNumAsc(@Param("param1") Integer index);

    @Select(value = "select movie.title,movie.score from movie " +
            "where movie.score>=#{param1} ORDER BY movie.score DESC ")
    List<Map<String,String>> getGreaterScoreMovie(@Param("param1") Integer scores);


    @Select(value="select movie.title,movie.score from movie " +
            "where movie.hadPositiveComment='T' ORDER BY movie.score DESC ")
    List<Map<String,String>> getPositiveMovie();

    @Select(value="with some as ( select * from year_type_num t where " +
            "t.year=#{param1}) " +
            "select s.type,some.movieNum from type s join some on s.type = some.type " +
            "order by some.movieNum DESC")
    List<Map<String,Integer>> getTimeTypePopular(@Param("param1") Integer time);
}
