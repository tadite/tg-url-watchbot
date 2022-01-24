package github.tadite.tg.tgchangebot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "watching_url", uniqueConstraints = @UniqueConstraint(columnNames = {"chat_id", "url"}))
@Entity
@Data
@NoArgsConstructor
public class WatchingUrl {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "url")
    private String url;
    //div[@class="col-24 col-xl-20 offset-xl-2"]/div[@class="row"]
    @Column(name = "xpath")
    private String xpath;

    public WatchingUrl(String chatId, String url, String xpath) {
        this.chatId = chatId;
        this.url = url;
        this.xpath = xpath;
    }

    public WatchingUrl(String chatId, String url) {
        this.chatId = chatId;
        this.url = url;
    }
}
