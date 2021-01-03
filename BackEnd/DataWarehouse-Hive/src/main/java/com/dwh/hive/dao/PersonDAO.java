package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PersonDAO extends BaseMapper<Person> {

    @Select(value = "select m.title,m.score from person s join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isDirector='T' AND s.name=#{param1} " +
            " order by m.score DESC")
    List<Map<String,Float>> getMoviesByDirector(@Param("param1") String name);


    @Select(value="select " +
            "    personID as personID," +
            "    name as name," +
            "    actorNum as actorNum," +
            "    directorNum as directorNum," +
            "    actorTogetherDirectorNum as actorTogetherDirectorNum," +
            "    joinNum as joinNum," +
            "    actorEitherDirector as actorEitherDirector," +
            "    actorAverageScore as actorAverageScore," +
            "    directorAverageScore as directorAverageScore" +
            " from person where person.directorNum>=#{param1} " +
            " AND person.actorEitherDirector=#{param2} ORDER BY person.directorNum DESC")
    List<Person> getGreaterDirector(@Param("param1") Integer number, @Param("param2") Character actorOrDirector);

    @Select(value="select " +
            "    personID as personID," +
            "    name as name," +
            "    actorNum as actorNum," +
            "    directorNum as directorNum," +
            "    actorTogetherDirectorNum as actorTogetherDirectorNum," +
            "    joinNum as joinNum," +
            "    actorEitherDirector as actorEitherDirector," +
            "    actorAverageScore as actorAverageScore," +
            "    directorAverageScore as directorAverageScore" +
            " from person where person.actorNum>=#{param1} " +
            " AND person.actorEitherDirector=#{param2} ORDER BY person.actorNum DESC")
    List<Person> getGreaterActor(@Param("param1") Integer number,@Param("param1") Character actorOrDirector);

    @Select(value = "select m.title,m.score from person s join relation c on s.personID = c.personID" +
            " join movie m on c.movieID = m.movieID" +
            " where c.isActor='T' AND s.name=#{param1}" +
            " ORDER BY m.score DESC")
    List<Map<String,Float>> getMoviesByActor(@Param("param1") String name);

}
