package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.YearRelease;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YearReleaseDAO extends BaseMapper<YearRelease> {
    @Select(value = "select " +
            "releaseYear as releaseYear," +
            "releaseNum as releaseNum" +
            "from year_release " +
            "where releaseYear!=0 ORDER BY releaseYear DESC ")
    List<YearRelease> findAllMovies();

}
