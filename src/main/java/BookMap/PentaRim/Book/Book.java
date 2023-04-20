package BookMap.PentaRim.Book;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Getter
@Setter
@ToString
public class Book {

    private String title;
    @JsonProperty("authors")
    private List<String> author;
    private String publisher;
    private List<String> hashTag;
    @JsonProperty("datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date publishedDay;
    private String isbn;
    @JsonProperty("thumbnail")
    private String image;
}
