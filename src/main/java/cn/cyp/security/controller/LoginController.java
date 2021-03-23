package cn.cyp.security.controller;
import cn.cyp.security.common.Result;
import cn.cyp.security.service.LoginService;
import cn.cyp.security.vo.VoLogin;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class LoginController{

    @Resource
    LoginService loginService;


    @PostMapping("/authentication")
    public Result login(@RequestBody VoLogin user){
            return loginService.login(user);
    }

    @GetMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(Authentication authentication){
        return loginService.getUserInfo(authentication);
    }

    @GetMapping("/getMenu")
    @ResponseBody
    public Result getMenu(Authentication authentication){
        return loginService.getMenu(authentication);
    }

}
