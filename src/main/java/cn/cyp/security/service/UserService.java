package cn.cyp.security.service;

import cn.cyp.security.common.Result;
import cn.cyp.security.pojo.User;

public interface UserService {

    /**
     * 根据用户id删除用户
     */
    Result delUser(Integer userId);

    /**
     * 批量删除用户
     */
    Result delUsers(String userIds);

    /**
     * 检查账号是否可用
     */
    Result checkUsername(String username);

    /**
     * 重置密码
     */
    Result resetPwd(Integer userId);


    /**
     * 查询所有用户
     * @return
     */
    Result selectUserList(String username, Integer status ,
                          Integer pageIndex, Integer pageSize,
                          String startDate,String endDate);

    /**
     * 添加用户
     */
    Result addUser(String username,String nickname,String password,int number,
                   int gender,String phone,int status,int classId);

    /**
     * 编辑用户
     */
    Result updateUser(User user);
}
