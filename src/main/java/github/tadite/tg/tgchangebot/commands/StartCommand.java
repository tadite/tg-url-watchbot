package github.tadite.tg.tgchangebot.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartCommand implements Command {

    @Override
    public void update(Message msg, TelegramLongPollingBot bot) {
        if (msg.getText().equals("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(msg.getChatId()));
            message.setText("Hello. This is start message");
            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
