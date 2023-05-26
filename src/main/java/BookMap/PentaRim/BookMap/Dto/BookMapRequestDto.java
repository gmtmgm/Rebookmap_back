package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.User.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Getter
@Setter
@Component
@NoArgsConstructor
public class BookMapRequestDto {

    private User user;
    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private HashSet<String> hashTag;

/*
    @Builder
    public BookMapRequestDto(Long bookMapId, User user, String bookMapTitle, String bookMapContent, HashSet<String> hashTag) {
        this.bookMapId = bookMapId;
        this.user = user;
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        this.hashTag = hashTag;
    }
 */
    @Builder
    public BookMapRequestDto(User user, String bookMapTitle, String bookMapContent, HashSet<String> hashTag) {
        this.user = user;
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        this.hashTag = hashTag;
    }

    public BookMapEntity toEntity() {
        return BookMapEntity.builder()
                .user(user)
                .bookMapTitle(bookMapTitle)
                .bookMapContent(bookMapContent)
                .hashTag(hashTag)
                .build();
    }

}