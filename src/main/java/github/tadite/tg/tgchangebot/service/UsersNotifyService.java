package github.tadite.tg.tgchangebot.service;

import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersNotifyService {

    private final WatchingUrlRepository watchingUrlRepository;

    private final BotService botService;

    @SneakyThrows
    public void notifyWatchingUsers(UrlContent urlContent, UrlXpath urlXpath) {
        List<WatchingUrl> watchingUrls = watchingUrlRepository.findAllByUrlXpath(urlXpath);
        for (WatchingUrl watchingUrl : watchingUrls) {
            botService.execute(getNotifyMessage(urlXpath, watchingUrl));
            botService.execute(getScreenshotMessage(urlContent, watchingUrl));
        }
    }

    private SendPhoto getScreenshotMessage(UrlContent urlContent, WatchingUrl watchingUrl) {
        return new SendPhoto(watchingUrl.getChatId(), new InputFile(urlContent.getScreenshot()));
    }

    private SendMessage getNotifyMessage(UrlXpath urlXpath, WatchingUrl watchingUrl) {
        return new SendMessage(watchingUrl.getChatId(), String.format("Url content has changed: %s", urlXpath));
    }
}
