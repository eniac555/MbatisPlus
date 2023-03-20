package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("tbl_user")//设置数据库表名
public class User {
    @TableField(value = "id")//数据库和实体类字段名不一致是使用
    @TableId(type = IdType.ASSIGN_ID)//有错，生成不了，全是0
    private long id;

    private String name;

    //@TableField(select = false)//不参与查询
    private String password;

    private Integer age;

    private String tel;

    @TableField(exist = false,select = false)
    // exist = false 表示数据库表中没有这个属性
    // select = false 表示这个字段的数据不参与查询
    private String gender;

    @TableLogic(value = "0",delval = "1")
    //0表示没有逻辑删除，1表示进行了逻辑删除
    private Integer deleted;

    @Version
    private Integer version;

}
