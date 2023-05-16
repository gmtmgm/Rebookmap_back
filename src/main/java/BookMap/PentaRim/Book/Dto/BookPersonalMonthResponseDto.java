package BookMap.PentaRim.Book.Dto;

import BookMap.PentaRim.Book.BookPersonal;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookPersonalMonthResponseDto {

    private Long id;
    private String isbn;
    private String image;
    private LocalDate endDate;

    public BookPersonalMonthResponseDto(BookPersonal bookPersonal){
        this.id = bookPersonal.getId();
        this.isbn = bookPersonal.getBook().getIsbn();
        this.image = bookPersonal.getBook().getImage();
        this.endDate = bookPersonal.getEndDate();

    }

}
