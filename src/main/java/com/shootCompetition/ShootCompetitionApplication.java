package com.shootCompetition;

import com.shootCompetition.websocket.server.WebSocketServer;
import com.shootCompetition.websocket.server.initalizer.WSDeviceChannelInitializer;
import com.shootCompetition.websocket.server.initalizer.WSFrontChannelInitializer;
import com.shootCompetition.websocket.server.initalizer.WSUserChannelInitializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan("com.shootCompetition.mapper")
@SpringBootApplication
public class ShootCompetitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShootCompetitionApplication.class, args);

        new WebSocketServer(new WSFrontChannelInitializer(),8887);
        new WebSocketServer(new WSDeviceChannelInitializer(),8888);

    }

}
