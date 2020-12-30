package com.example.demo.dao;

import com.example.demo.Entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface movieRepository extends JpaRepository<movie, Integer>,
        JpaSpecificationExecutor<movie> {

    List<movie> findByMovieIDInOrderByScoreDesc(List<Integer> temp);

    @Query(value=
            "select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=?1 AND releaseMonth=?2 AND releaseDay=?3" +
                    " ORDER BY m.score DESC ",
            nativeQuery = true)
    List<Map<String,Float>> getAltimate1(Integer year, Integer month, Integer day);

    @Query(value=
            "select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=?1 AND releaseMonth=?2" +
                    " ORDER BY m.score DESC",
            nativeQuery = true)
    List<Map<String,Float>> getAltimate2(Integer year, Integer month);

    @Query(value=
            "select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=?1 ORDER BY m.score DESC",
            nativeQuery = true)
    List<Map<String,Float>> getAltimate3(Integer year);

    @Query(value=
            "select m.title,m.score " +
                    "from release_time s join product p on s.timeID = p.timeID " +
                    "join movie m on m.movieID = p.movieID " +
                    "where releaseYear=?1 AND releaseMonth in ?2" +
                    " ORDER BY m.score DESC",
            nativeQuery = true)
    List<Map<String,Float>> getAltimate4(Integer year, List<Integer> month);

    List<movie> findAllByTitleOrderByScoreDesc(String title);


    //ID Score has?

}
