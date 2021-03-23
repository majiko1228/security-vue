package cn.cyp.security.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_menu")

/**
 * 菜单表
 */

public class Menu {
    /**
     * 菜单id 主键
     */
    @TableId(value = "menu_id",type = IdType.AUTO)
    private int menuId;

    /**
     * 菜单名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 父菜单id
     */
    @TableField(value = "menu_pid")
    private int menuPid;

    /**
     * 是否为叶子节点
     */
    @TableField(value = "is_leaf")
    private int isLeaf;

    /**
     * url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private int sort;

    /**
     * 菜单层级
     */
    @TableField(value = "level")
    private int level;

    /**
     * 菜单是否启用  0启用  1禁用
     */
    @TableField(value = "status")
    private int status;

    /**
     * 菜单是否显示 0显示  1不显示
     */
    @TableField(value = "visible")
    private int visible;

    /**
     * 子菜单
     */
    private List<Menu> children = new ArrayList<>();
}
