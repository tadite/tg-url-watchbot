package github.tadite.tg.tgchangebot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;

@Data
@AllArgsConstructor
public class UrlContent {

    private WebElement element;

    public String getHtml(){
        return element.getText();
    }

    public File getScreenshot(){
        return element.getScreenshotAs(OutputType.FILE);
    }

}
