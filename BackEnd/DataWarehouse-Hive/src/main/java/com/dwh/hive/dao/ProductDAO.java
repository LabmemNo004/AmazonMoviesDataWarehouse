package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDAO extends BaseMapper<Product> {

    List<Product> findByTimeIDIn(@Param("param1") List<Integer> temp);

    List<Product> findByMovieIDInOrderByTimeIDDesc(@Param("param1") List<Integer> temp);
}
