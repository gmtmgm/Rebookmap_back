package BookMap.PentaRim.Book;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   //serialize 오류 해결 방법
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long id;

    private String title;
    private String author;
    private String publisher;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date publishedDay;
    @Column(unique = true)
    private String isbn;

    private String image;

    private String description;


    public Book(){
    }
    public Book(String title, String author, String publisher, String isbn, String image, Date publishedDay, String description){
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.title = title;
        this.image = image;
        this.description = description;
        this.publishedDay = publishedDay;

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
