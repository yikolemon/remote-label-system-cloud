package cn.zko0.remotelabel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class DateHandlerApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(DateHandlerApplication.class,args);
    }
}
