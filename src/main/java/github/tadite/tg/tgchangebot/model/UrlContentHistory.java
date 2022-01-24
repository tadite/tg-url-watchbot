package github.tadite.tg.tgchangebot.model;

import github.tadite.tg.tgchangebot.service.UrlXpath;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class UrlContentHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Embedded
    private UrlXpath urlXpath;

    @Column(columnDefinition="TEXT")
    private String content;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public UrlContentHistory(UrlXpath urlXpath, String content) {
        this.urlXpath = urlXpath;
        this.content = content;
    }

}
