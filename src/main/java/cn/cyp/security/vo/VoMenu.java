package cn.cyp.security.vo;

import lombok.Data;

import java.util.List;

/**
 * vue 菜单
 */
@Data
public class VoMenu {
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单url
     */
    private String index;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单子节点
     */
    private List<VoMenu> subs;
}
