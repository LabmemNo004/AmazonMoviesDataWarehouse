package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.YearMonthPK;
import com.dwh.hive.pojo.YearMonthRelease;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YearMonthDAO extends BaseMapper<YearMonthRelease> {

    @Select(value="select releaseYear as releaseYear, releaseMonth as releaseMonth, releaseNum as releaseNum" +
            " from year_month_release " +
            " where releaseYear=#{param1} AND releaseMonth!=0 order by releaseMonth ASC ")
    List<YearMonthRelease> findYearMovie(@Param("param1") Integer year);
}
