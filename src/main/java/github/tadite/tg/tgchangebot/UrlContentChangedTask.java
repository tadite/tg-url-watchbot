package github.tadite.tg.tgchangebot;

import org.springframework.scheduling.annotation.Scheduled;

public class UrlContentChangedTask {

    @Scheduled(fixedRateString = "15m")
    public void runTask() {

    }
}
