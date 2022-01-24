package github.tadite.tg.tgchangebot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class UrlXpath {

    String url;

    String xpath;

}
