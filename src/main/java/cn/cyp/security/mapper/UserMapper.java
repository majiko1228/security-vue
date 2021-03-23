package cn.cyp.security.mapper;

import cn.cyp.security.pojo.Menu;
import cn.cyp.security.pojo.MyUserDetails;
import cn.cyp.security.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface UserMapper extends BaseMapper<User> {

    //根据用户名查询用户
    MyUserDetails getUserByUserName(String username);

    //根据用户名查询用户角色
    List<String> getRoleByUsername(String username);

    //根据用户角色查询用户权限
//    List<String> getAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    //根据用户名查询用户权限（唯一标识url）
    List<String> getUrlsByUserName(String username);

    //根据用户角色，查询相应菜单
    List<Menu> getMenuByRoles(@Param("roles") List<String> roles);

    /**
     * 删除用户角色关系
     */
    void delRole(int userId);

    /**
     * 查询所有用户（分页）
     */
    List<User> selectUserList(Page<User> page ,String username, Integer status ,
                              String startDate, String endDate);

    /**
     *查询记录数
     */
    int selectUserListCount();

    /**
     * 添加用户角色
     */
    void addUserRole(int userId,int roleId);

}
