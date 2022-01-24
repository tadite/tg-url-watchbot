package github.tadite.tg.tgchangebot;

import github.tadite.tg.tgchangebot.service.UrlContent;
import github.tadite.tg.tgchangebot.service.WebDriverClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@Disabled
@SpringBootTest
class WebDriverClientTest {

    @Autowired
    private WebDriverClient webDriverClient;

    @Test
    void getContent() {
        UrlContent content = webDriverClient.getContent("https://xn--80aald4bq.xn--d1aqf.xn--p1ai/apartment/filter/place_id-is-ta4sitjp/apply/",
                "//div[@class=\"col-24 col-xl-20 offset-xl-2\"]/div[@class=\"row\"]");
        String html = content.getHtml();
        File screenshot = content.getScreenshot();
    }
}