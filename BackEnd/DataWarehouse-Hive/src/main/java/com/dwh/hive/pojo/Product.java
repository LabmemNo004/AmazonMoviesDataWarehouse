package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableField("productID") //指定和表中cust_id字段的映射关系
    private String productID;

    @TableField("movieID")
    private Integer movieID;

    @TableField("timeID")
    private Integer timeID;

    @TableField("type")
    private String type;

    @TableField("format")
    private String format;

    @TableField("price")
    private String price;

    public Product(String productID, Integer movieID,
                   Integer timeID, String type,
                   String format, String price) {
        this.productID = productID;
        this.movieID = movieID;
        this.timeID = timeID;
        this.type = type;
        this.format = format;
        this.price = price;
    }

    public Product() {}
}
