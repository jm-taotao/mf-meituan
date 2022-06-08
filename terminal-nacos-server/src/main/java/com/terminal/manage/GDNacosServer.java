package com.terminal.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author Jyt
 * @date 2021/9/24
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class GDEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(GDEurekaServer.class,args);
    }
}
