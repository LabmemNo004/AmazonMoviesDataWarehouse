package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.ReleaseTime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ReleaseTimeDAO extends BaseMapper<ReleaseTime> {

    List<ReleaseTime> findByReleaseYearAndReleaseMonthAndReleaseDay(
            Integer year, Integer month, Integer day);


    ReleaseTime findByTimeID(Integer temp);

    @Select(value = "with thisDirector as (" +
            "    select s.personID from person s" +
            "    where s.name=#{param2})," +
            " TypeTimeMovie as (" +
            "    select productID,releaseYear,type,movieID," +
            "           product.timeID as time" +
            "    from product join" +
            "        release_time rt on rt.timeID = product.timeID" +
            "    where rt.releaseYear=#{param1} AND product.type=#{param3}" +
            ")" +
            "select DISTINCT m.title,m.score from thisDirector tD join relation r" +
            "    on tD.personID=r.personID join movie m on m.movieID = r.movieID" +
            "    join TypeTimeMovie TT on TT.movieID=m.movieID" +
            "    join person p on r.personID = p.personID" +
            "    ORDER BY m.score DESC;")
    List<Map<String,Float>>getTimeDirectorType(@Param("param1") Integer year,
                                               @Param("param2") String name,
                                               @Param("param3") String type);
}
