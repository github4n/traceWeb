package wang.wangby;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import wang.wangby.trace.config.MarketConfig;

import java.io.File;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class TraceApp implements CommandLineRunner {

    @Value("${test}")
    private boolean test;

    public static void main(String[] args) {
        File file=new File("/opt/config/traceConfig");
        if(!file.exists()){
            throw new RuntimeException("找不到配置文件："+file.getAbsolutePath());
        }
        String text= FileUtil.readString(file,"UTF-8");
        String[] keys=text.trim().split("\n");
        MarketConfig.API_KEY=keys[0].trim();
        MarketConfig.SECRET_KEY=keys[1].trim();
        MarketConfig.PASSWORD=keys[2].trim();

        SpringApplication.run(TraceApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        if(test){
            log.info("当前是测试环境不会下单");
        }
    }
}
