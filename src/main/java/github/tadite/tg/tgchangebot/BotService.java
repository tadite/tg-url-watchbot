package github.tadite.tg.tgchangebot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotService {

    private final Bot bot;

    public BotService(BotConfigurationProperties config, CommandsInvoker commandsInvoker) {
        Bot bot = new Bot(config, commandsInvoker);
        bot.init();
        this.bot = bot;
    }

    public Message execute(SendMessage message) throws TelegramApiException {
        return bot.execute(message);
    }
}
