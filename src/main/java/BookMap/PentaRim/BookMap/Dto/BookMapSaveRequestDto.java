package BookMap.PentaRim.BookMap.Dto;


import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.User.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@NoArgsConstructor
public class BookMapSaveRequestDto {

    private String bookMapTitle; //북맵이름
    private String bookMapContent; //북맵설명
    private User user;

    @Builder
    public BookMapSaveRequestDto(String bookMapTitle, String bookMapContent, User user) {
        this.bookMapTitle = bookMapTitle;
        this.bookMapContent = bookMapContent;
        this.user = user;
    }

    public BookMapEntity toEntity() {
        return BookMapEntity.builder()
                .bookMapTitle(bookMapTitle)
                .bookMapContent(bookMapContent)
                .user(user)
                .build();
    }

}