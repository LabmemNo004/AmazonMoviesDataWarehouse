package com.dwh.hive.pojo;

import java.io.Serializable;

public class YearMonthPK implements Serializable {

    private Integer releaseYear;
    private Integer releaseMonth;

    public YearMonthPK() {
    }

    public YearMonthPK(Integer releaseYear, Integer releaseMonth) {
        super();
        this.releaseYear = releaseYear;
        this.releaseMonth = releaseMonth;
    }
    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
        result = PRIME * result + ((releaseMonth == null) ? 0 : releaseMonth.hashCode());
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

        final YearMonthPK other = (YearMonthPK)obj;
        if(releaseYear == null){
            if(other.releaseYear != null){
                return false;
            }
        }else if(!releaseYear.equals(other.releaseYear)){
            return false;
        }
        if(releaseMonth == null){
            if(other.releaseMonth != null){
                return false;
            }
        }else if(!releaseMonth.equals(other.releaseMonth)){
            return false;
        }
        return true;
    }


    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(Integer releaseMonth) {
        this.releaseMonth = releaseMonth;
    }
}
