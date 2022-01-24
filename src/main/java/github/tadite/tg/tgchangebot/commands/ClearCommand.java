package github.tadite.tg.tgchangebot.commands;

import github.tadite.tg.tgchangebot.repo.WatchingUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class ClearCommand implements Command {

    private final WatchingUrlRepository watchingUrlRepository;

    @Override
    public void update(Message msg, TelegramLongPollingBot bot) {
        try {
            if (msg.getText().startsWith("/clear")) {
                String chatId = msg.getChatId().toString();
                watchingUrlRepository.deleteAllByChatId(chatId);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Url list cleared");
                bot.execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
