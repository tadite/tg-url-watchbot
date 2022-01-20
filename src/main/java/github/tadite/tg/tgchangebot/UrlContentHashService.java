package github.tadite.tg.tgchangebot;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UrlContentHashService {

    private WebClient webClient;

    public String getUrlContent(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        DataBufferUtils.write(dataBuffer, destination,
//                StandardOpenOption.CREATE)
//                .share().block();
    }
}
