package com.ruoyi;

import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.mapper.SymStudentMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 启动程序
 * 
 * @author sym
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{


    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);

    }





}
