package cn.cyp.security.vo;


import lombok.Data;

@Data
public class VoLogin {
    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户验证码
     */
    private String code;

}
