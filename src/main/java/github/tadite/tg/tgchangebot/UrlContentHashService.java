package github.tadite.tg.tgchangebot;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class UrlContentHashService {

    private WebClient webClient;

    public String getUrlContentHash(String url) {
        Flux<DataBuffer> dataBuffer = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

//        DataBufferUtils.write(dataBuffer, destination,
//                StandardOpenOption.CREATE)
//                .share().block();
    }
}
