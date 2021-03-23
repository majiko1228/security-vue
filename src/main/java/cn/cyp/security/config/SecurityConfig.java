package cn.cyp.security.config;


import cn.cyp.security.jwt.filter.JwtAuthenticationTokenFilter;
import cn.cyp.security.jwt.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)//方法级别的安全控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    MyUserDetailsService myUserDetailsService;
    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    protected void configure(HttpSecurity http) throws Exception {
        http    .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().and()//跨域资源共享
                .csrf().disable() //禁用跨站csrf攻击防御

                //基于JWT，不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //认证失败处理
                //.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                // 退出登录
                //.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler).and()
                .authorizeRequests()// 授权配置
                //登录访问，验证码
                .antMatchers("/authentication","/refreshtoken","/authentication/require","/code/image").permitAll()//不需要通过登录验证就可以被访问的资源路径
                .antMatchers("/","/index","index.html").authenticated()//登录之后可以访问主页

        //.anyRequest().access("@rabcService.hasPermission(request,authentication)")

        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 静态资源访问
     * @param web
     */
    @Override
    public void configure(WebSecurity web){
        //静态资源释放,不经过过滤器
        web.ignoring().antMatchers("/static/**","/css/**","/fonts/**","/image/**","/js/**");
    }
    /**
     * 解决 无法直接注入 AuthenticationManager
     * @return
     * @throws Exception
     */

//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 解决跨域
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        // * 表示对所有的地址可以访问
        configuration.addAllowedOrigin("*");
        // 跨域的请求头
        configuration.addAllowedHeader("*");
        // 跨域的请求方法
        configuration.addAllowedMethod("*");

//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));  // 前端端口号
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
//        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 配置 可以访问的地址
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
