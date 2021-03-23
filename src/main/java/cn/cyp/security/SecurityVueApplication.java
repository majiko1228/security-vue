package cn.cyp.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@MapperScan("cn.cyp.security.mapper")
public class SecurityVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityVueApplication.class, args);
    }

}
