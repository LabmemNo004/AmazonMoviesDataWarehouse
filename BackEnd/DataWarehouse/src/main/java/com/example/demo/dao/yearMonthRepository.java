package com.example.demo.dao;

import com.example.demo.Entity.year_monthPK;
import com.example.demo.Entity.year_month_release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface yearMonthRepository extends JpaRepository<year_month_release, year_monthPK>,
        JpaSpecificationExecutor<year_month_release>
{
    @Override
    year_month_release getOne(year_monthPK year_monthPK);

    List<year_month_release> findByReleaseYearLessThan(Integer year);

    List<year_month_release> findByReleaseYearOrderByReleaseMonthDesc(Integer year);

}
