package cn.cyp.security;

import cn.cyp.security.mapper.ClassesMapper;
import cn.cyp.security.mapper.UserMapper;
import cn.cyp.security.pojo.User;
import cn.cyp.security.service.LoginService;
import cn.cyp.security.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SecurityVueApplicationTests {

    @Resource
    ClassesMapper classesMapper;

    @Resource
    UserMapper userMapper;
    @Resource
    LoginService loginService;
    @Resource
    UserService userService;
    @Test
    void contextLoads() {

    }

    @Test
    public void test1 (){
        //userMapper.addUserRole(7,1);
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("username","admin");
//        User user = userMapper.selectOne(queryWrapper);
//        System.out.println("user = " + user);
        //System.out.println(userMapper.selectUserByUsername("admin"));
    }
    @Test
     public void testUser (){
         List<User> userList = new ArrayList<>();
         Page<User> userPage = new Page<>(1,5);
         userList = userMapper.selectUserList(userPage,"",1,null,null);
         System.out.println(userList);

    }

}
