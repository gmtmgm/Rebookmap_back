package BookMap.PentaRim.Book;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Book {

    private String title;
    private List<String> author;
    private String publisher;
    private List<String> hashTag;
    private String publishedDay;
    private Long isbn;
    private String image;
}
