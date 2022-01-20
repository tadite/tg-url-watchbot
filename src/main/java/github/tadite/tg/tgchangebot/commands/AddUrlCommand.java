package github.tadite.tg.tgchangebot.commands;

import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AddUrlCommand implements Command {

    private final WebClient webClient;

    private final WatchingUrlRepository watchingUrlRepository;

    @Override
    public void update(Message msg, TelegramLongPollingBot bot) {
        try {
            if (msg.getText().startsWith("/add")) {
                String url = msg.getText().split(" ")[1];

                try {
                    testUrl(url);
                    String chatId = msg.getChatId().toString();
                    watchingUrlRepository.save(new WatchingUrl(chatId, url));
                    bot.execute(getAddedMessage(msg, url));
                } catch (Exception e) {
                    bot.execute(getErrorMessage(msg, url, e));
                }

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage getAlreadyWatchingMessage(Message msg, String url) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(String.format("You already watching url %s", url));
        return message;
    }

    private SendMessage getAddedMessage(Message msg, String url) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(String.format("Added url %s to watch list", url));
        return message;
    }

    private SendMessage getErrorMessage(Message msg, String url, Exception e) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(msg.getChatId()));
        message.setText(String.format("Error testing url %s\n%s", url, e));
        return message;
    }

    private HttpStatus testUrl(String url) {
        return webClient.get()
                .uri(url)
                .exchangeToMono(clientResponse -> Mono.just(clientResponse.statusCode()))
                .block();
    }
}
