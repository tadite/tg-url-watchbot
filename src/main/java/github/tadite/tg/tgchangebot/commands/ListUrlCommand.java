package github.tadite.tg.tgchangebot.commands;

import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListUrlCommand implements Command {

    private final WatchingUrlRepository watchingUrlRepository;

    @Override
    public void update(Message msg, TelegramLongPollingBot bot) {
        try {
            if (msg.getText().startsWith("/list")) {
                String chatId = msg.getChatId().toString();
                List<WatchingUrl> urls = watchingUrlRepository.findAllByChatId(chatId);
                bot.execute(getListMessage(msg, urls));

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage getListMessage(Message msg, List<WatchingUrl> urls) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(getListText(urls));
        return message;
    }

    private String getListText(List<WatchingUrl> urls) {
        if (urls.isEmpty())
            return "Watching list is empty";

        return String.format("Watching list:\n %s", getUrlsString(urls));
    }

    private String getUrlsString(List<WatchingUrl> urls) {
        return urls.stream()
                .map(WatchingUrl::getUrl)
                .collect(Collectors.joining("\n"));
    }
}
