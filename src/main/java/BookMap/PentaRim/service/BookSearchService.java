package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.SearchListBooks;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class BookSearchService {

    @Value("${kakao-admin-key}")
    String adminKey;

    public Book searchBooks(String keyword){

        try {
            ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
            RestTemplate restTemplate = new RestTemplate(factory);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "KakaoAK " + adminKey);
            HttpEntity<?> httpEntity = new HttpEntity(httpHeaders);

            URI targetUrl = UriComponentsBuilder
                    .fromUriString("https://dapi.kakao.com/v3/search/book") //기본 url
                    .queryParam("query", keyword) //인자
                    .build()
                    .encode(StandardCharsets.UTF_8) //한글로 받으려고 인코딩
                    .toUri();
            ResponseEntity<SearchListBooks> resultResponseEntity = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, SearchListBooks.class);
            //현재로서는 받을 때 list로 밖에 없음 -> SearchListBooks 객체는 필요함
            SearchListBooks searchListBooks = resultResponseEntity.getBody();

            return searchListBooks.getBooks().get(0);  //isbn으로 검색시 고유의 id이므로 하나의 책밖에 안뜸

        }catch (HttpStatusCodeException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){

            }
        }throw  new IllegalStateException("api 호출 실패");

    }
}

