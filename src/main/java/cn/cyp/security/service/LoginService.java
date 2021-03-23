package cn.cyp.security.service;

import cn.cyp.security.common.Result;
import cn.cyp.security.vo.VoLogin;
import org.springframework.security.core.Authentication;


public interface LoginService {

    //用户登录
    Result login(VoLogin user);
    //用户用户信息
    Result getUserInfo(Authentication authentication);
    //获取用户菜单
    Result getMenu(Authentication authentication);
    //获取所有用户
    Result getList(Integer pageIndex,Integer pageSize,String username, Integer status, String startDate, String endDate);

}
