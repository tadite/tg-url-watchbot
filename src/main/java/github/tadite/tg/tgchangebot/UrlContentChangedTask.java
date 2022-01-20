package github.tadite.tg.tgchangebot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class UrlContentChangedTask {

    @Scheduled(fixedRateString = "15m")
    public void runTask() {
        log.info("Starting task...");
    }
}
