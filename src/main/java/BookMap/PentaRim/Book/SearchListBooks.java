package BookMap.PentaRim.Book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchListBooks {
    @JsonProperty("documents")
    List<Item> books = new ArrayList<>();


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item{
        private String title;
        @JsonProperty("authors")
        private List<String> author;
        private String publisher;
        @JsonProperty("datetime")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date publishedDay;
        private String isbn;
        @JsonProperty("thumbnail")
        private String image;
        @JsonProperty("contents")
        private String description;
    }
}
