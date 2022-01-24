package github.tadite.tg.tgchangebot.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebDriverClient {

    private final WebDriver webDriver;

    public UrlContent getContent(UrlXpath urlXpath) {
        return getContent(urlXpath.getUrl(), urlXpath.getXpath());
    }

    @SneakyThrows
    public synchronized UrlContent getContent(String url, String xpath) {
        webDriver.get(url);
        WebElement element = webDriver.findElement(By.xpath(xpath));
        return new UrlContent(element);
    }
}
