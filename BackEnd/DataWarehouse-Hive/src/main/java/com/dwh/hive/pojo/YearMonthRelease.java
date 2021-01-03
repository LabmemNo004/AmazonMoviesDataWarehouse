package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("year_month_release")
public class YearMonthRelease {

    @TableField(value = "releaseYear")
    private Integer releaseYear;

    @TableField(value = "releaseMonth")
    private Integer releaseMonth;

    @TableField(value = "releaseNum")
    private Integer releaseNum;
}
