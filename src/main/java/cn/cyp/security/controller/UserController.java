package cn.cyp.security.controller;


import cn.cyp.security.common.Result;
import cn.cyp.security.pojo.User;
import cn.cyp.security.service.LoginService;
import cn.cyp.security.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    LoginService loginService;
    @Resource
    UserService userService;

    @GetMapping("/getList")
    public Result getList(Integer pageIndex,Integer pageSize, String username, Integer status, String startDate, String endDate){
        return loginService.getList(pageIndex,pageSize,username,status, startDate,endDate);
    }

    /**
     * 删除用户（根据用户id）
     * @param userId
     * @return
     */
    @DeleteMapping("/delete/{userId}")
    @ResponseBody
    public Result delete(@PathVariable("userId") int userId){
        return userService.delUser(userId);
    }


    /**
     * 重置密码
     * @param userId
     * @return
     */
    @RequestMapping("/resetPwd/{userId}")
    @ResponseBody
    public Result resetPwd(@PathVariable("userId") int userId){
        System.out.println("userId = " + userId);
        return userService.resetPwd(userId);
    }
    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(String userIds){
        return userService.delUsers(userIds);
    }

    /**
     * 检查账号是否可用
     */
    @PostMapping("/checkUsername")
    @ResponseBody
    public Result checkUsername(String username){
        return userService.checkUsername(username);
    }

    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(String username,String nickname,String password,int number,
                          int gender,String phone,int status,int classId){
        return userService.addUser(username,nickname,password,number,gender,phone,status,classId);
    }

    @RequestMapping("/selectUserList")
    @ResponseBody
    public Result selectUserList(String username , Integer status ,
                                 String startDate,String endDate,
                                 Integer pageIndex,Integer pageSize){

        return userService.selectUserList(username ,status,
                pageIndex,pageSize,startDate,endDate);
    }

//    @PutMapping("/updateUser")
//    @ResponseBody
//    public Result updateUser(int userId, String username,String nickname,String password,int number,
//                             int gender,String phone,int status,int classId){
//        return userService.updateUser(userId,username,nickname,password,number,gender,phone,status,classId);
//    }

    @PutMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@RequestBody User user){
        //System.out.println("user = " + user);
        return userService.updateUser(user);
    }


}
