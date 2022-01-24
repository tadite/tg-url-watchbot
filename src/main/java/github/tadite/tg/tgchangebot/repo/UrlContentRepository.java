package github.tadite.tg.tgchangebot.repo;

import github.tadite.tg.tgchangebot.model.UrlContentHistory;
import github.tadite.tg.tgchangebot.service.UrlXpath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlContentRepository extends JpaRepository<UrlContentHistory, UUID> {

    Optional<UrlContentHistory> findFirstByUrlXpathOrderByLastModifiedDateDesc(UrlXpath urlXpath);

}