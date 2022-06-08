package com.terminal.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Jyt
 * @date 2021/9/24
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.terminal.manage.mapper"})
@EnableDiscoveryClient
public class MTSystemServer {
    public static void main(String[] args) {
        SpringApplication.run(MTSystemServer.class,args);
    }
}
