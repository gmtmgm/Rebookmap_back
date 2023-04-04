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
    private BookState bookstate;   //bookpersonal로 따로 만들기보다는 book 자체에 넣는 건 어떨까요?(default는 NOT, WISH/READING/DONE 4가지로)
}
