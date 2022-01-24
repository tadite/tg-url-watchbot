package github.tadite.tg.tgchangebot.service;

import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersNotifyService {

    private final WatchingUrlRepository watchingUrlRepository;

    private final BotService botService;

    @SneakyThrows
    public void notifyWatchingUsers(String url) {
        List<WatchingUrl> watchingUrls = watchingUrlRepository.findAllByUrl(url);
        for (WatchingUrl watchingUrl : watchingUrls) {
            botService.execute(getNotifyMessage(url, watchingUrl));
        }
    }

    private SendMessage getNotifyMessage(String url, WatchingUrl watchingUrl) {
        return new SendMessage(watchingUrl.getChatId(), String.format("Url content has changed: %s", url));
    }
}
