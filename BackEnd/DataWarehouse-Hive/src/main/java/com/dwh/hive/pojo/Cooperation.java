package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("cooperation")
public class Cooperation implements Serializable {
        @TableField("leftPersonID")
        private Integer leftPersonID;

        @TableField("rightPersonID")
        private Integer rightPersonID;

        @TableField("cooperateNum")
        private Integer cooperateNum;

        @TableField("leftPersonType")
        private Character leftPersonType;

        @TableField("rightPersonType")
        private Character rightPersonType;

        @TableField("leftPersonName")
        private String leftPersonName;

        @TableField("rightPersonName")
        private String rightPersonName;

        public Cooperation() {
        }

        public Cooperation(Integer leftPersonID, Integer rightPersonID,
                           Integer cooperateNum, Character leftPersonType,
                           Character rightPersonType, String leftPersonName,
                           String rightPersonName) {
                this.leftPersonID = leftPersonID;
                this.rightPersonID = rightPersonID;
                this.cooperateNum = cooperateNum;
                this.leftPersonType = leftPersonType;
                this.rightPersonType = rightPersonType;
                this.leftPersonName = leftPersonName;
                this.rightPersonName = rightPersonName;
        }

}
