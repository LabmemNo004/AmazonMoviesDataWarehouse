package com.example.demo.dao;

import com.example.demo.Entity.year_monthPK;
import com.example.demo.Entity.year_month_release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface yearMonthRepository extends JpaRepository<year_month_release, year_monthPK>,
        JpaSpecificationExecutor<year_month_release>
{
    @Override
    year_month_release getOne(year_monthPK year_monthPK);

    @Query(value="select * from year_month_release " +
            " where releaseYear=?1 AND releaseMonth!=0 order by releaseMonth ASC ",nativeQuery = true)
    List<year_month_release> findYearMovie(Integer year);


//    List<year_month_release> findByReleaseYearOrderByReleaseMonthAsc(Integer year);

}
