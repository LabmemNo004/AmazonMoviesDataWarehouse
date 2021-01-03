package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.Relation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RelationDAO extends BaseMapper<Relation> {
    @Select(value="select p.name from movie q join relation r on q.movieID = r.movieID " +
            "join person p on p.personID = r.personID " +
            "where q.title=#{param1} AND r.isDirector=#{param2} ORDER BY p.name ASC ")
    List<String> getAssociateDirector(@Param("param1") String title, @Param("param2") Character identify);

    @Select(value="select p.name from movie q join relation r on q.movieID = r.movieID " +
            "join person p on p.personID = r.personID " +
            "where q.title=#{param1} AND r.isActor=#{param2} ORDER BY p.name ASC")
    List<String> getAssociateActor(@Param("param1") String title, @Param("param2") Character identify);
}
