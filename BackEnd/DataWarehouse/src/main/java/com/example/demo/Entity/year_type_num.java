package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "year_type_num")
@IdClass(year_typePK.class)
@Proxy(lazy = false)
//取消懒加载避免奇怪bug
public class year_type_num implements Serializable {
    @Id
    @Column(name = "year")
    private Integer year;

    @Id
    @Column(name = "type")
    private Integer type;

    @Column(name = "movieNum")
    private Integer movieNum;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(Integer movieNum) {
        this.movieNum = movieNum;
    }
}
