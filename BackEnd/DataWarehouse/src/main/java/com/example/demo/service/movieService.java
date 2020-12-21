package com.example.demo.service;

import com.example.demo.Entity.movie;
import com.example.demo.Entity.product;
import com.example.demo.Entity.release_time;
import com.example.demo.dao.movieRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.dao.releaseTimeRepository;
import com.example.demo.dao.yearMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class movieService {

    @Autowired
    private yearMonthRepository yearmonthRepository;

    @Autowired
    private releaseTimeRepository releaseTimeRepository;

    @Autowired
    private movieRepository movierepository;

    @Autowired
    private productRepository productrepository;

    public List<movie> findBySpecifidTime(Integer year,Integer month,Integer day)
    {
        List<movie> temp=new ArrayList<>();
        //tempid->productid->movieid
//        List<Integer> timeid=releaseTimeRepository.gettimeIDByYMD(year, month, day);
//        List<Integer> MOVIEid1=productrepository.getmovieIDBy(timeid);
        List<release_time> time=releaseTimeRepository.findByReleaseYearAndReleaseMonthAndReleaseDay(
                year, month, day);
        List<Integer> tempID=new ArrayList<>();
        List<Integer> tempID1=new ArrayList<>();
        for(release_time some:time)
        {
            tempID.add(some.getTimeID());
        }

        List<product> products=productrepository.findByTimeIDIn(tempID);

        for(product some:products)
        {
            tempID1.add(some.getMovieID());
        }

        temp=movierepository.findByMovieIDIn(tempID1);

        return temp;
    }

}
