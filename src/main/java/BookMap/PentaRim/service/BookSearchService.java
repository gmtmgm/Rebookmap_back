package BookMap.PentaRim.service;

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

    //현재는 모든 키워드(제목, 작가, isbn 등등) 다 받을 수 있습니다
    //나중에 isbn만 받는다면 굳이 SearchListBooks로 list<searchbook>에 넣지않고
    //바로 searchbook 객체로 넣을 수도 있을 것 같습니다.
    @Value("${kakao-admin-key}")
    String adminKey;

    public SearchListBooks searchBooks(String keyword){

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
                    .encode(StandardCharsets.UTF_8) //인코딩
                    .toUri();
            ResponseEntity<SearchListBooks> resultResponseEntity = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, SearchListBooks.class);
            SearchListBooks searchListBooks = resultResponseEntity.getBody();
            return searchListBooks;
        }catch (HttpStatusCodeException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){

            }
        }throw  new IllegalStateException("api 호출 실패");

    }
}

