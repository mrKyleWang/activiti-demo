package top.kylewang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kyle.Wang
 * 2018/2/20 0020 11:10
 */
@SpringBootApplication
@MapperScan("top.kylewang.mapper")
public class ActivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }
}
