package github.tadite.tg.tgchangebot.repo;

import github.tadite.tg.tgchangebot.model.WatchingUrl;
import github.tadite.tg.tgchangebot.service.UrlXpath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface WatchingUrlRepository extends JpaRepository<WatchingUrl, UUID> {

    List<WatchingUrl> findAllByChatId(String chatId);

    @Transactional
    void deleteAllByChatId(String chatId);

    List<WatchingUrl> findAllByUrlXpath(UrlXpath urlXpath);

}