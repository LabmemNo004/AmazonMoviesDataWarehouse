package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("year_type_num")
public class YearTypeNum {
    @TableField("year")
    private Integer year;

    @TableField("type")
    private Integer type;

    @TableField("movieNum")
    private Integer movieNum;
}
