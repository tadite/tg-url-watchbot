package github.tadite.tg.tgchangebot.commands;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {

    void update(Message msg, TelegramLongPollingBot bot);

}
