package github.tadite.tg.tgchangebot.model;

import github.tadite.tg.tgchangebot.service.UrlXpath;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Embedded
    private UrlXpath urlXpath;

    private LocalDateTime lastModifiedDate;

    public WatchingUrl(String chatId, UrlXpath urlXpath) {
        this.chatId = chatId;
        this.urlXpath = urlXpath;
        this.lastModifiedDate = LocalDateTime.now();
    }

}
