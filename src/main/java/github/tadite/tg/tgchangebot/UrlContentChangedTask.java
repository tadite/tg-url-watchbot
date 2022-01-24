package github.tadite.tg.tgchangebot;

import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlContentChangedTask {

    private final WatchingUrlRepository watchingUrlRepository;

    private final UrlContentHashService urlContentHashService;

    private final UsersNotifyService usersNotifyService;

//    @Scheduled(fixedRateString = "PT30M")
    @Scheduled(fixedDelay = 10000)
    public void runTask() {
        log.info("Starting task...");
        Map<String, List<WatchingUrl>> urls = watchingUrlRepository.findAll().stream()
                .collect(Collectors.groupingBy(WatchingUrl::getUrl));

        for (String url : urls.keySet()) {
            boolean changed = urlContentHashService.checkUrl(url);
            if (changed){
                usersNotifyService.notifyWatchingUsers(url);
            }
        }
    }
}
