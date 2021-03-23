package cn.cyp.security.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor//使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor//使用后创建一个无参构造函数
@TableName(value = "tb_user")

/**
 * 用户表
 */

public class User {

    /**
     * 用户主键id
     */
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户性别
     */
    @TableField(value = "gender")
    private int gender;
    /**
     * 学号
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 用户手机
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 用户状态
     */
    @TableField(value = "enable")
    private Integer enable;

    /**
     * 注册时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 数据更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 班级id
     */
    @TableField("class_id")
    private int classId;

    /**
     * 班级名称
     */
    @TableField(exist = false)
    private String className;

}
