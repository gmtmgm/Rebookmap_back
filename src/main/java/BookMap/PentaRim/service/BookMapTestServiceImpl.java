package BookMap.PentaRim.service;

import BookMap.PentaRim.BookMap.BookMapTest;
import BookMap.PentaRim.BookMap.HashTag;
import BookMap.PentaRim.BookMap.MapHashTag;
import BookMap.PentaRim.BookMap.dto.BookMapResponseDto;
import BookMap.PentaRim.BookMap.dto.TagRequestDto;
import BookMap.PentaRim.Repository.BookMapTestRepository;
import BookMap.PentaRim.Repository.HashtagRepository;
import BookMap.PentaRim.Repository.MapTagRepository;
import BookMap.PentaRim.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMapTestServiceImpl implements BookMapTestService{
    final MapTagRepository mapTagRepository;
    final BookMapTestRepository bookMapTestRepository;
    final HashtagRepository hashtagRepository;
    final UserRepository userRepository;

    //해시태그 변경은 다 삭제하고 다시 넣는 구조
    @Override
    @Transactional
    public void tagssave(Long id, TagRequestDto tagRequestDto){
        BookMapTest bookMapTest = bookMapTestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 북맵이 없습니다."));
        List<String> tags = tagRequestDto.getTags();
        for(String tag: tags){
            if(hashtagRepository.existsByTag(tag)){
                HashTag hashTag = hashtagRepository.findByTag(tag).orElseThrow(
                        () -> new IllegalArgumentException("해당 해시태그가 없습니다."));
                mapTagRepository.save(new MapHashTag(hashTag, bookMapTest));
            }else{
                HashTag hashTag = new HashTag(tag);
                mapTagRepository.save(new MapHashTag(hashTag, bookMapTest));
            }
        }
    }

    @Override
    @Transactional
    public void tagsUpdate(Long id, TagRequestDto tagRequestDto){
        BookMapTest bookMapTest = bookMapTestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 북맵이 없습니다."));
        tagsDelete(id);
        tagssave(id, tagRequestDto);
    }

    @Override
    @Transactional
    public void tagsDelete(Long id){
        BookMapTest bookMapTest = bookMapTestRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 북맵이 없습니다."));
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByBookMapTest(bookMapTest);
        mapTagRepository.deleteAllByBookMapTest(bookMapTest);
        List<HashTag> hashTags = new ArrayList<>();
        List<Long> hashTagIds = new ArrayList<>();
        for(MapHashTag mapHashTag: mapHashTags){
            hashTagIds.add(mapHashTag.getHashTag().getId());
        }
        for(Long hashTagId: hashTagIds){
            if(!mapTagRepository.existsByHashTag_Id(hashTagId)){
                hashtagRepository.deleteById(hashTagId);
            }
        }
    }
    @Override
    @Transactional
    public List<BookMapResponseDto> findBookMapByTag(String tag){
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByHashTag_Tag(tag);
        List<BookMapTest> bookMapTests = new ArrayList<>();
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
        for(MapHashTag mapHashTag: mapHashTags){
            bookMapTests.add(mapHashTag.getBookMapTest());
        }
        for(BookMapTest bookMapTest: bookMapTests){
            bookMapResponseDtos.add(new BookMapResponseDto(bookMapTest.getId()));
        }
        return bookMapResponseDtos;
    }
}
