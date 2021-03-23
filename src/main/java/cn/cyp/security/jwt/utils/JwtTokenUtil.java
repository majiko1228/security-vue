package cn.cyp.security.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 生成token，以及根据token获取用户信息
 */
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {
    /**
     * 令牌秘钥
     */
    private String secret;
    /**
     * 令牌有效期（默认30分钟）
     */
    private int expireTime;
    /**
     * 令牌自定义标识
     */
    private String header;

    /**
     * 创建令牌
     */
    public String createToken(UserDetails userDetails){
        Map<String,Object>  claims = new HashMap<>(2);
        claims.put("sub",userDetails.getUsername());
        claims.put("created",new Date());
        return generateToken(claims);
    }
    /**
     * 从claims生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        //当前时间
        Date nowDate = new Date();
        System.out.println("nowDate = " + nowDate);
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expireTime * 1000);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /*
    *
    * 获取用户信息
    * */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
//            System.out.println(e);
            username = null;
        }
        return username;
    }
    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    /**
     * 判断令牌是否过期
     * @param token
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token){
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            //过期时间在当前时间之前，未过期
            System.out.println("过期时间"+expiration);
            System.out.println("当前时间"+new Date());
            return expiration.before(new Date());
        }catch (Exception e){
            return false;
        }
    }
    /*
    * 刷新令牌
    * @param token
    * @return 新令牌
    * */
    public String refreshToken(String token){
        String refreshToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created",new Date());
            refreshToken = generateToken(claims);
        }catch (Exception e){
            refreshToken = null;
        }
        return refreshToken;
    }
    /**
     * 判断令牌
     * @param token
     * @param userDetails
     * @return 是否有效
     */
    public Boolean validateToken (String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }
}
