package github.tadite.tg.tgchangebot.commands;

import github.tadite.tg.tgchangebot.UrlContentClient;
import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class AddUrlCommand implements Command {

    private final UrlContentClient urlContentClient;

    private final WatchingUrlRepository watchingUrlRepository;

    @Override
    public void update(Message msg, TelegramLongPollingBot bot) {
        try {
            if (msg.getText().startsWith("/add")) {
                String afterCommand = getPartAfter(msg.getText(), " ");
                String url = getPartBefore(afterCommand, " ");
                String xpath = getPartAfter(afterCommand, " ");

                try {
                    urlContentClient.getContent(url, xpath);
                    String chatId = msg.getChatId().toString();
                    watchingUrlRepository.save(new WatchingUrl(chatId, url, xpath));
                    bot.execute(getAddedMessage(msg, url, xpath));
                } catch (Exception e) {
                    bot.execute(getErrorMessage(msg, url, xpath, e));
                }

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getPartAfter(String str, String delimeter) {
        int i = str.indexOf(delimeter)+1;
        return str.substring(i);
    }

    private String getPartBefore(String str, String delimeter) {
        int i = str.indexOf(delimeter);
        return str.substring(0, i);
    }

    private SendMessage getAlreadyWatchingMessage(Message msg, String url) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(String.format("You already watching url %s", url));
        return message;
    }

    private SendMessage getAddedMessage(Message msg, String url, String xpath) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(String.format("Added url %s to watch list with xpath %s", url, xpath));
        return message;
    }

    private SendMessage getErrorMessage(Message msg, String url, String xpath, Exception e) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(String.format("Error testing url %s with xpath %s\n%s", url, xpath, e));
        return message;
    }
}
