package github.tadite.tg.tgchangebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TgChangeBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgChangeBotApplication.class, args);
    }

}
