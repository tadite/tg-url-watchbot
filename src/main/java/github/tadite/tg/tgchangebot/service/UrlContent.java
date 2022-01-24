package github.tadite.tg.tgchangebot.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Data
@RequiredArgsConstructor
public class UrlContent {

    private final WebElement element;
    private final ConcurrentMap<String, Object> cache = new ConcurrentHashMap<>();

    public String getHtml() {
        return (String) cache.computeIfAbsent("html", key -> element.getText());
    }

    public File getScreenshot() {
        return (File) cache.computeIfAbsent("screenshot", key -> element.getScreenshotAs(OutputType.FILE));
    }

}
