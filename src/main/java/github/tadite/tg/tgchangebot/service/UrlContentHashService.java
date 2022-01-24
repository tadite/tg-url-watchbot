package github.tadite.tg.tgchangebot.service;

import github.tadite.tg.tgchangebot.model.UrlContent;
import github.tadite.tg.tgchangebot.repo.UrlContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlContentHashService {

    private final WebClient webClient;

    private final UrlContentRepository urlContentRepository;

    public boolean checkUrl(String url) {
        String content = getUrlContent(url);
        Optional<UrlContent> lastSavedUrlHash = urlContentRepository.findFirstByUrlOrderByChangeTimeDesc(url);
        if (lastSavedUrlHash.isPresent()) {
            if (isHashChanged(content, lastSavedUrlHash.get())){
                urlContentRepository.save(new UrlContent(url, content));
                return true;
            }
        } else {
            urlContentRepository.save(new UrlContent(url, content));
            return true;
        }
        return false;
    }

    private boolean isHashChanged(String contentHash, UrlContent urlContent) {
        return !urlContent.getContent().equals(contentHash);
    }
}
