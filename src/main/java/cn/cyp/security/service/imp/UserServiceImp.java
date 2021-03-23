package cn.cyp.security.service.imp;

import cn.cyp.security.common.Constance;
import cn.cyp.security.common.Result;
import cn.cyp.security.common.Utils;
import cn.cyp.security.mapper.UserMapper;
import cn.cyp.security.pojo.User;
import cn.cyp.security.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)   // 方法抛出异常，就会回滚，数据库里的数据也会回滚。
public class UserServiceImp implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public Result delUser(Integer userId) {
        userMapper.deleteById(userId);
        userMapper.delRole(userId);
        return Result.success();
    }

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    @Override
    public Result delUsers(String userIds) {
        String[] split =userIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String userId:split){
                ids.add(Integer.parseInt(userId));
                userMapper.delRole(Integer.parseInt(userId));
        }
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    /**
     * 检查账号
     * @param username
     * @return
     */
    @Override
    public Result checkUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        Integer count = userMapper.selectCount(queryWrapper);
        if (count>0){
            return Result.success(400,"账号已存在",null);
        }
        return Result.success(200,"账号可使用",null);
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @Override
    public Result resetPwd(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        User user = userMapper.selectById(userId);
        user.setPassword(Utils.getPassword(Constance.RESET_PASSWORD));
        userMapper.update(user,queryWrapper);
        return Result.success(200,"密码重置成功，新密码为123456",null);
    }

    /**
     * 加载用户列表
     * @param username
     * @param status
     * @param pageIndex
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Result selectUserList(String username, Integer status ,
                                 Integer pageIndex, Integer pageSize,
                                 String startDate,String endDate) {
//        System.out.println("username = " + username);
//        System.out.println("status = " + status);
//        System.out.println("pageIndex = " + pageIndex);
//        System.out.println("pageSize = " + pageSize);
//        System.out.println("startDate = " + startDate);
//        System.out.println("endDate = " + endDate);
        if(startDate.isEmpty()||endDate.isEmpty()){
                endDate = null;
        }
        Page<User> page = new Page<>(pageIndex,pageSize);

        List<User> userList = userMapper.selectUserList(page,username,status,startDate,endDate);
        //System.out.println("userList = " + userList);
        page.setRecords(userList);
        return Result.success(page);
    }

    @Override
    public Result addUser(String username, String nickname, String password,
                          int number, int gender, String phone,
                          int status, int classId) {
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("username",username);
        Integer count = userMapper.selectCount(queryWrapper1);
        if (count>0){
            return Result.fail(400,"用户已存在",null);
        }
        else {
            password = Utils.getPassword(password);
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setNickname(nickname);
            newUser.setPassword(password);
            newUser.setNumber(number);
            newUser.setGender(gender);
            newUser.setPhone(phone);
            newUser.setStatus(status);
            newUser.setCreateTime(new Date());
            newUser.setUpdateTime(new Date());
            newUser.setClassId(classId);
            userMapper.insert(newUser);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("username",username);
            User user = userMapper.selectOne(queryWrapper);
            userMapper.addUserRole(user.getUserId(),4);
            return Result.success();
        }
    }

    @Override
    public Result updateUser(User user) {
        user.setPassword(Utils.getPassword(user.getPassword()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //System.out.println(user);
        userMapper.updateById(user);
        return Result.success();
    }
}
