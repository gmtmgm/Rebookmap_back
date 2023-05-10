package BookMap.PentaRim.Book;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   //serialize 오류 해결 방법
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long id;

    private String title;
    @JsonProperty("authors")
    private String author;
    private String publisher;

    @JsonProperty("datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date publishedDay;
    private String isbn;
    @JsonProperty("thumbnail")
    private String image;

    public Book(){
    }
    public Book(String title, String author, String publisher, String isbn, String image){
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.title = title;
        this.image = image;

    }
    public String splitIsbn(String isbn){
        if (isbn.contains(" ")){
            String[] stringList = isbn.split(" ");
            return stringList[0];
        }
        else{
            return isbn;
        }
    }


}
