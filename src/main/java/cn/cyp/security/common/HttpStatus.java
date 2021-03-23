package cn.cyp.security.common;

/**
 * 返回状态码
 */
public class HttpStatus {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 验证码错误
     */
    public static final int ERRORCODE = 406;

    /**
     * 密码错误
     */
    public static final int ERRORPWD = 407;

    /**
     * token过期
     */
    public static final int  INVALID_TOKEN=401;
}
