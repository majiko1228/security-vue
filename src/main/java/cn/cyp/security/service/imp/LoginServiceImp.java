package cn.cyp.security.service.imp;


import cn.cyp.security.common.HttpStatus;
import cn.cyp.security.common.Result;
import cn.cyp.security.common.Utils;
import cn.cyp.security.jwt.utils.JwtTokenUtil;
import cn.cyp.security.mapper.UserMapper;
import cn.cyp.security.pojo.Menu;
import cn.cyp.security.pojo.MyUserDetails;
import cn.cyp.security.pojo.User;
import cn.cyp.security.service.LoginService;
import cn.cyp.security.vo.VoLogin;
import cn.cyp.security.vo.VoMenu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    private UserMapper userMapper;

    //用户登录
    @Override
    public Result login(VoLogin user) {
        String username = user.getUsername();
        System.out.println("username = " + username);
        String password = user.getPassword();
        System.out.println("password = " + password);
        try {
            UsernamePasswordAuthenticationToken uptoken =
                    new UsernamePasswordAuthenticationToken(username,password);
            Authentication authentication = authenticationManager.authenticate(uptoken);
//            System.out.println(uptoken);
            //System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("authentication = " + authentication);
        }catch (AuthenticationException e){
            System.out.println(e);
            return Result.fail(HttpStatus.ERRORPWD,"用户名或密码错误",null);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User myUser = userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
        if(myUser.getStatus()==1) {
            System.out.println("停用");
            return Result.fail(456, "账号已停用，请联系管理员", null);
        }

        String token = jwtTokenUtil.createToken(userDetails);
        //System.out.println("token = " + token);
        return Result.success(token);
    }

    //获取用户信息
    @Override
    public Result getUserInfo(Authentication authentication) {
        Map<String,Object> map = new HashMap<>();
        MyUserDetails  myUserDetails =(MyUserDetails)authentication.getPrincipal();
        map.put("roles",myUserDetails.getRoles());
        map.put("userInfo",myUserDetails.getUser());
        map.put("urls",myUserDetails.getUrls());
        return Result.success(map);
    }

    //获取用户菜单
    @Override
    public Result getMenu(Authentication authentication) {
        Map<String,Object> map = new HashMap<>();
        MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
        List<String> roles = myUserDetails.getRoles();
        List<Menu> menus = userMapper.getMenuByRoles(roles);
        List<Menu> menuList = Utils.getMenu(menus,0);
        System.out.println("menuList = " + menuList);
        List<VoMenu> voMenuList = Utils.buildVoMenus(menuList);
        map.put("menuList",voMenuList);
        return Result.success(map);
    }

    //获取所有用户
    @Override
    public Result getList(Integer pageIndex,Integer pageSize, String username, Integer status, String startDate, String endDate) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.and(wrapper -> {
            wrapper.like("username",username).or().like("nickname",username);
        });
        if (status!=null){
            queryWrapper.eq("status",status);
        }
        if(!startDate.isEmpty() && !endDate.isEmpty()){
            queryWrapper.between("create_time",startDate,endDate);
        }

        Page<User> page =new Page<>(pageIndex,pageSize);
        IPage<User> userIPage = userMapper.selectPage(page,queryWrapper);
//        List records = userIPage.getRecords();
//        records.forEach(System.out::println);
        return Result.success(userIPage);
    }

}
