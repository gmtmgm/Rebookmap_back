package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookListEntity;
import BookMap.PentaRim.BookMap.BookMapDetailEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
public class BookListRequestDto {

//    private BookMapDetailEntity bookMapDetail;
    private Book book;
    private int index;

    @Builder
    public BookListRequestDto(/*BookMapDetailEntity bookMapDetail, */Book book, int index){
//        this.bookMapDetail = bookMapDetail;
        this.book = book;
        this.index = index;
    }

    public BookListEntity toEntity(){
        return BookListEntity.builder()
//                .bookMapDetail(bookMapDetail)
                .book(book)
                .index(index)
                .build();
    }

}