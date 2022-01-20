package github.tadite.tg.tgchangebot.repo;

import github.tadite.tg.tgchangebot.model.UrlContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlContentRepository extends JpaRepository<UrlContent, UUID> {

    Optional<UrlContent> findFirstByUrlOrderByChangeTime(String url);

}