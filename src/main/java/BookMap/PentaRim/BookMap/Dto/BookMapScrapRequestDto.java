package BookMap.PentaRim.BookMap.Dto;


import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.BookMapScrapEntity;
import BookMap.PentaRim.User.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Getter
@Setter
@Component
@NoArgsConstructor
public class BookMapScrapRequestDto {

    private BookMapEntity bookMap;
    private User user;
    private LocalDateTime bookMapSaveTime;

    @Builder
    public BookMapScrapRequestDto(BookMapEntity bookMap, User user, LocalDateTime bookMapSaveTime) {
        this.bookMap = bookMap;
        this.user = user;
        this.bookMapSaveTime = bookMapSaveTime;
    }

    public BookMapScrapEntity toEntity() {
        return BookMapScrapEntity.builder()
                .bookMap(bookMap)
                .user(user)
                .bookMapSaveTime(bookMapSaveTime)
                .build();
    }

}