package com.example.demo.Entity;

import java.io.Serializable;

public class relationPK implements Serializable {
    
    private Integer movieID;
    private Integer personID;

    public relationPK(Integer movieID, Integer personID) {
        this.movieID = movieID;
        this.personID = personID;
    }
    public relationPK(){
        
    }

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((movieID == null) ? 0 : movieID.hashCode());
        result = PRIME * result + ((personID == null) ? 0 : personID.hashCode());
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

        final relationPK other = (relationPK)obj;
        if(movieID == null){
            if(other.movieID != null){
                return false;
            }
        }else if(!movieID.equals(other.movieID)){
            return false;
        }
        if(personID == null){
            if(other.personID != null){
                return false;
            }
        }else if(!personID.equals(other.personID)){
            return false;
        }
        return true;
    }
    
    
    
    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }
}
