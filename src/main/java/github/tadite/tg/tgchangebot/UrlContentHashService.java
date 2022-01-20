package github.tadite.tg.tgchangebot;

import github.tadite.tg.tgchangebot.model.UrlContent;
import github.tadite.tg.tgchangebot.repo.UrlContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlContentHashService {

    private final WebClient webClient;

    private final UrlContentRepository urlContentRepository;

    public boolean checkUrl(String url) {
        String contentHash = getUrlContentHash(url);
        Optional<UrlContent> lastSavedUrlHash = urlContentRepository.findFirstByUrlOrderByChangeTime(url);
        if (lastSavedUrlHash.isPresent()) {
            if (isHashChanged(contentHash, lastSavedUrlHash.get())){
                urlContentRepository.save(new UrlContent(url, contentHash));
                return true;
            }
        } else {
            urlContentRepository.save(new UrlContent(url, contentHash));
            return true;
        }
        return false;
    }

    private boolean isHashChanged(String contentHash, UrlContent urlContent) {
        return !urlContent.getContent().equals(contentHash);
    }

    @SneakyThrows
    private String getUrlContentHash(String url) {
        String content = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String body = StringUtils.substringBetween(content,
                "<div class=\"col-24 col-xl-20 offset-xl-2\">", "</div>");

        MessageDigest md5 = MessageDigest.getInstance("SHA-256");
        md5.update(body.getBytes(StandardCharsets.UTF_8));
        return new String(md5.digest());
    }
}
