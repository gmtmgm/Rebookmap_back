package BookMap.PentaRim.BookMap.Dto;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.BookListEntity;
import BookMap.PentaRim.BookMap.BookMapDetailEntity;
import lombok.Getter;

@Getter
public class BookListResponseDto {

    private Long bookListId;
    private BookMapDetailEntity bookMapDetail;
    private Book book;
    private int index;

    public BookListResponseDto(BookListEntity bookListEntity){
        this.bookListId = bookListEntity.getBookListId();
        this.bookMapDetail = bookListEntity.getBookMapDetail();
        this.book = bookListEntity.getBook();
        this.index = bookListEntity.getIndex();
    }

}