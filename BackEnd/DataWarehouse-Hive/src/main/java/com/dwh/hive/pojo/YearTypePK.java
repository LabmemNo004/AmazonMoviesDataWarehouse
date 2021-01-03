package com.dwh.hive.pojo;

import java.io.Serializable;

public class YearTypePK implements Serializable {

    private Integer year;
    private Integer type;

    public YearTypePK(){
    }

    public YearTypePK(Integer year, Integer type) {
        this.year = year;
        this.type = type;
    }

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((year == null) ? 0 : year.hashCode());
        result = PRIME * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final YearTypePK other = (YearTypePK)obj;
        if(year == null){
            if(other.year != null){
                return false;
            }
        }else if(!year.equals(other.year)){
            return false;
        }
        if(type == null){
            if(other.type != null){
                return false;
            }
        }else if(!type.equals(other.type)){
            return false;
        }
        return true;
    }


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
}

