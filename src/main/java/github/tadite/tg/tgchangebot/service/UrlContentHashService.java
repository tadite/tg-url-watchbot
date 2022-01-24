package github.tadite.tg.tgchangebot.service;

import github.tadite.tg.tgchangebot.model.UrlContentHistory;
import github.tadite.tg.tgchangebot.repo.UrlContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlContentHashService {

    private final WebDriverClient webDriverClient;

    private final UrlContentRepository urlContentRepository;

    public UrlContent checkUrl(UrlXpath urlXpath) {
        UrlContent content = webDriverClient.getContent(urlXpath);
        Optional<UrlContentHistory> lastSavedUrlHash = urlContentRepository.findFirstByUrlXpathOrderByLastModifiedDateDesc(urlXpath);
        if (lastSavedUrlHash.isPresent()) {
            if (isHashChanged(content, lastSavedUrlHash.get())) {
                urlContentRepository.save(new UrlContentHistory(urlXpath, content.getHtml()));
                return content;
            }
        } else {
            urlContentRepository.save(new UrlContentHistory(urlXpath, content.getHtml()));
            return content;
        }
        return null;
    }

    private boolean isHashChanged(UrlContent content, UrlContentHistory urlContentHistory) {
        return !urlContentHistory.getContent().equals(content.getHtml());
    }
}
