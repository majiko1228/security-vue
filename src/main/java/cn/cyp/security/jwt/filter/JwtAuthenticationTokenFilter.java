package cn.cyp.security.jwt.filter;


import cn.cyp.security.exception.CustomException;
import cn.cyp.security.exception.CustomExceptionType;
import cn.cyp.security.jwt.service.MyUserDetailsService;
import cn.cyp.security.jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("URI:" + request.getRequestURI());
        //从网络请求头中获取token
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        System.out.println("jwtToken = " + jwtToken);
        if (!StringUtils.isEmpty(jwtToken)) {
            //从token中获取用户名
            String username = jwtTokenUtil.getUserNameFromToken(jwtToken);
            System.out.println("getUserNameFromToken:"+username);
            if (username == null) {
                System.out.println("抛出异常");
                resolver.resolveException(request,response,null,new CustomException(CustomExceptionType.INVALID_TOKEN,"token过期"));
                return;
                //throw new CustomException(CustomExceptionType.INVALID_TOKEN,"token过期");
            }
            if (username != null &&//用户没有被security认证过
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                //加载用户信息
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                try {
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        //System.out.println("token合法");
                        //token合法，没有过期，给该用户进行授权
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (Exception e) {
                    System.out.println("token已过期：" + e);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}