package BookMap.PentaRim.BookMap.Dto;


import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.User.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Component
@NoArgsConstructor
public class BookMapSaveRequestDto {

    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private List<String> hashTag;
    private User user;
    private LocalDateTime bookMapSaveTime;
    private boolean share;

    @Builder
    public BookMapSaveRequestDto(String bookMapTitle, String bookMapContent, List<String> hashTag, User user, LocalDateTime bookMapSaveTime, boolean share) {
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        this.hashTag = hashTag;
        this.user = user;
        this.bookMapSaveTime = bookMapSaveTime;
        this.share = share;
    }

    public BookMapEntity toEntity() {
        return BookMapEntity.builder()
                .bookMapTitle(bookMapTitle)
                .bookMapContent(bookMapContent)
                .user(user)
                .bookMapSaveTime(bookMapSaveTime)
                .share(share)
                .build();
    }

}