package cn.cyp.security.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_user_role")
public class Role {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "user_id")
    private int userId;

    @TableField(value = "role_id")
    private int roleId;

}
