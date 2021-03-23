package cn.cyp.security.common;

import cn.cyp.security.mapper.UserMapper;
import cn.cyp.security.pojo.Menu;
import cn.cyp.security.vo.VoMenu;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {

    @Resource
    UserMapper userMapper;
    /**
     * 获取密码
     */
    public static String getPassword(String pwd){
        return new BCryptPasswordEncoder().encode(pwd.trim());
    }

    /**
     *
     *获取用户菜单
     */
    public static List<Menu> getMenu(List<Menu> menus,Integer parentId){
        List<Menu> menuList = new ArrayList<>();
        for (Iterator<Menu> menuIterator = menus.iterator();menuIterator.hasNext();){
            Menu menu = (Menu)menuIterator.next();
            if(menu.getMenuPid()==parentId){
                recursion(menus,menu);
                menuList.add(menu);
            }
        }
        return menuList;
    }


    /**
     * 递归
     */

    public  static void recursion(List<Menu> menus,Menu menu){
        List<Menu> childList = getChildMenus(menus,menu);
        menu.setChildren(childList);
        for (Menu menu1:childList){
            if(hasChild(menus,menu1)){
                recursion(menus,menu1);
            }
        }
    }

    /**
     * 获取该菜单的子节点
     * @param menus
     * @param menu
     * @return
     */
    public static List<Menu> getChildMenus(List<Menu> menus,Menu menu){
            List<Menu> childreMenus = new ArrayList<>();
            Iterator<Menu> menuIterator = menus.iterator();
            while (menuIterator.hasNext()){
                Menu menu1 = (Menu) menuIterator.next();
                if(menu1.getMenuPid() == menu.getMenuId()){
                    childreMenus.add(menu1);
                }
            }
            return childreMenus;
    }

    /**
     * 判断是否还有子节点
     */
    public static boolean hasChild(List<Menu> menus,Menu menu){
            return getChildMenus(menus,menu).size() >0 ? true : false;
    }

    /**
     * 构建前端菜单
     * @param menuList
     * @return
     */
    public static List<VoMenu> buildVoMenus(List<Menu> menuList) {
        List<VoMenu> voMenuList = new ArrayList<>();
        for (Iterator<Menu> menuIterator = menuList.iterator();menuIterator.hasNext();){
            Menu menu = (Menu) menuIterator.next();

            if (menu.getVisible() == 1)
                continue;

            VoMenu voMenu = new VoMenu();
            voMenu.setTitle(menu.getTitle());
            voMenu.setIcon(menu.getIcon());
            if(menu.getChildren().size() == 0){
                voMenu.setIndex(menu.getUrl().substring(1));
                voMenuList.add(voMenu);
            }else {
                voMenu.setIndex(menu.getMenuId()+"");
                voMenu.setSubs(getChildVoMenus(menu.getChildren()));
                voMenuList.add(voMenu);
            }
        }
        return voMenuList;
    }
    /**
     * 获取孩子菜单
     * @param menus
     * @return
     */
    public static List<VoMenu> getChildVoMenus(List<Menu> menus){
        List<VoMenu> voMenuList = new ArrayList<>();
        Iterator<Menu> menuIterator = menus.iterator();
        while(menuIterator.hasNext()){
            Menu menu = menuIterator.next();
            VoMenu vueMenu = new VoMenu();
            vueMenu.setTitle(menu.getTitle());
            vueMenu.setIndex(menu.getUrl().substring(1));
            vueMenu.setIcon(menu.getIcon());
            voMenuList.add(vueMenu);
        }
        return voMenuList;
    }

}
