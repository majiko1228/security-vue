package cn.cyp.security.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String username;//用户名
    private String password;//密码
    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 登录时间
     * @return
     */
    private Long loginTime;

    /**
     * 过期时间
     * @return
     */
    private Long expireTime;

    /**
     *用户角色
     */
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * 用户信息
     * @return
     */
    private User user;

    /**
     * 权限列表
     * urls
     */
    List<String> urls;

    private boolean accountNonExpired = true;//账户是否过期
    private boolean accountNonLocked = true;//账户是否锁定
    private boolean CredentialsNonExpired = true;//凭证是否过期
    private boolean enabled = true;//用户是否可用
    Collection<? extends GrantedAuthority> authorities;//用户权限集合

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "MyUserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", CredentialsNonExpired=" + CredentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        CredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
