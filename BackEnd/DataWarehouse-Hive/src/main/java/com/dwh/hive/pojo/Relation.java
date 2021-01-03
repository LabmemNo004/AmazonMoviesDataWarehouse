package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("relation")
public class Relation {
    @TableField("personID") //指定和表中cust_id字段的映射关系
    private Integer personID;

    @TableField("movieID")
    private String movieID;

    @TableField("isActor")
    private String isActor;

    @TableField("isDirector")
    private String isDirector;

}
