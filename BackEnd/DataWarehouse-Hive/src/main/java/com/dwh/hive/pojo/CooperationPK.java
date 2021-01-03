package com.dwh.hive.pojo;

import java.io.Serializable;

public class CooperationPK implements Serializable {

    private Integer leftPersonID;
    private Integer rightPersonID;

    public CooperationPK(){

    }

    public CooperationPK(Integer leftPersonID, Integer rightPersonID) {
        this.leftPersonID = leftPersonID;
        this.rightPersonID = rightPersonID;
    }

    //  ***重写hashCode与equals方法***  划重点！
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((leftPersonID == null) ? 0 : leftPersonID.hashCode());
        result = PRIME * result + ((rightPersonID == null) ? 0 : rightPersonID.hashCode());
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

        final CooperationPK other = (CooperationPK)obj;
        if(leftPersonID == null){
            if(other.leftPersonID != null){
                return false;
            }
        }else if(!leftPersonID.equals(other.leftPersonID)){
            return false;
        }
        if(rightPersonID == null){
            if(other.rightPersonID != null){
                return false;
            }
        }else if(!rightPersonID.equals(other.rightPersonID)){
            return false;
        }
        return true;
    }

    public Integer getLeftPersonID() {
        return leftPersonID;
    }

    public void setLeftPersonID(Integer leftPersonID) {
        this.leftPersonID = leftPersonID;
    }

    public Integer getRightPersonID() {
        return rightPersonID;
    }

    public void setRightPersonID(Integer rightPersonID) {
        this.rightPersonID = rightPersonID;
    }
}
