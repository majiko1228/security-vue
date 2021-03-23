package cn.cyp.security.jwt.service;


import cn.cyp.security.mapper.UserMapper;
import cn.cyp.security.pojo.MyUserDetails;
import cn.cyp.security.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUserDetails myUserDetails = new MyUserDetails();

//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        User user = userMapper.selectOne(queryWrapper);

        myUserDetails = userMapper.getUserByUserName(username);
        if (myUserDetails == null){
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        //加载基础用户信息
        //System.out.println("myUserDetails = " + myUserDetails);

        //加载用户角色列表
        List<String> roleCodes = userMapper.getRoleByUsername(username);

        //获取用户权限
        List<String> authorities = userMapper.getUrlsByUserName(username);
        myUserDetails.setUrls(authorities);
//        System.out.println("authorities = " + authorities);
        myUserDetails.setRoles(roleCodes);

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
        myUserDetails.setUser(user);
        //通过用户角色列表加载用户的资源权限列表
//        List<String> authorties = userMapper.getAuthorityByRoleCodes(roleCodes);
//        myUserDetails.setUrls(authorties);
//
//        //角色是一种特殊的权限，ROLE_前缀
//        roleCodes = roleCodes.stream()
//                .map(rc->"ROLE_" + rc)
//                .collect(Collectors.toList());
//        authorties.addAll(roleCodes);
//
//        myUserDetails.setAuthorities(
//                //把逗号分隔的字符串转换为权限列表
//                AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",authorties))
//        );
        return myUserDetails;
    }
}