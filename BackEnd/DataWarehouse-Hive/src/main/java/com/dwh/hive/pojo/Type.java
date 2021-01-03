package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("type")
public class Type {
    @TableField("type") //指定和表中cust_id字段的映射关系
    private String type;

    @TableField("movieNum")
    private Integer movieNum;
}
