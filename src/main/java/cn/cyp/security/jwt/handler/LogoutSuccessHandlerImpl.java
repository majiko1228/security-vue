//package cn.cyp.security.jwt.handler;
//
//
//import cn.cyp.security.common.HttpStatus;
//import cn.cyp.security.jwt.utils.JwtTokenUtil;
//import com.alibaba.fastjson.JSON;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 退出处理类
// */
//@Configuration
//public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        UserDetails userDetails = jwtTokenUtil.getLoginUser(request);
//        if (loginUser != null){
////            String username = loginUser.getUsername();
//            // 删除用户缓存
//            jwtTokenUtil.delLoginUser(loginUser.getToken());
//        }
//        response.setStatus(200);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().print(JSON.toJSONString(RespObj.succ(HttpStatus.SUCCESS,"退出成功",null)));
//    }
//}
