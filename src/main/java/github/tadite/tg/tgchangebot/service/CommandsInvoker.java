package github.tadite.tg.tgchangebot.service;

import github.tadite.tg.tgchangebot.commands.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandsInvoker {

    private final List<Command> commands;

    public void update(Message msg, TelegramLongPollingBot bot) {
        commands.forEach(command -> command.update(msg, bot));
    }

}
