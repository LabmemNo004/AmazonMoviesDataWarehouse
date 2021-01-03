package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.Movie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface MovieDAO extends BaseMapper<Movie> {

    @Select("select " +
            "    movieID as movieID," +
            "    title as title," +
            "    productNum as productNum," +
            "    directorNum as directorNum," +
            "    actorNum as actorNum," +
            "    commentNum as commentNum," +
            "    score as score," +
            "    hadPositiveComment as hasPositiveComment" +
            "from movie" +
            "where movieID=#{param1}" +
            "order by score desc")
    List<Movie> findByMovieIDInOrderByScoreDesc(@Param("param1") List<Integer> temp);

    @Select("select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=#{param1} AND releaseMonth=#{param2} AND releaseDay=#{param3}" +
                    " ORDER BY m.score DESC ")
    List<Map<String,Float>> getAltimate1(@Param("param1") Integer year, @Param("param2") Integer month, @Param("param3") Integer day);

    @Select("select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=#{param1} AND releaseMonth=#{param2}" +
                    " ORDER BY m.score DESC")
    List<Map<String,Float>> getAltimate2(@Param("param1") Integer year, @Param("param2") Integer month);

    @Select("select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=#{param1} ORDER BY m.score DESC")
    List<Map<String,Float>> getAltimate3(@Param("param1") Integer year);

    @Select(value=
            "select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=#{param1} AND releaseMonth in #{param2}" +
                    " ORDER BY m.score DESC")
    List<Map<String,Float>> getAltimate4(@Param("param1") Integer year, @Param("param2") List<Integer> month);

    @Select("select " +
            "    movieID as movieID," +
            "    title as title," +
            "    productNum as productNum," +
            "    directorNum as directorNum," +
            "    actorNum as actorNum," +
            "    commentNum as commentNum," +
            "    score as score," +
            "    hadPositiveComment as hasPositiveComment" +
            " from movie" +
            " where title=#{param1}" +
            " order by score desc")
    List<Movie> findAllByTitleOrderByScoreDesc(@Param("param1") String title);
}
