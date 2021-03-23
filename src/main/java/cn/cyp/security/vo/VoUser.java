package cn.cyp.security.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class VoUser {


    /**
     * 用户id，唯一标识
     */

    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户性别
     */
    private String gender;
    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户手机
     */
    private Integer phone;
    /**
     * 用户邮箱
     */

    private String email;

//    /**
//     * 用户状态
//     */
//    @TableField(value = "status")
//    private Integer status;

    /**
     * 用户状态
     */

    private Integer enable;

    /**
     * 注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;



}
