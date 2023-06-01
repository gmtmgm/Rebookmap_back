package BookMap.PentaRim.service;

import BookMap.PentaRim.BookMap.BookMapEntity;
import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto1;
import BookMap.PentaRim.BookMap.HashTag;
import BookMap.PentaRim.BookMap.MapHashTag;
import BookMap.PentaRim.BookMap.Dto.TagRequestDto;
import BookMap.PentaRim.Repository.BookMapRepository;
import BookMap.PentaRim.Repository.HashtagRepository;
import BookMap.PentaRim.Repository.MapTagRepository;
import BookMap.PentaRim.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMapTestServiceImpl implements BookMapTestService{
    final MapTagRepository mapTagRepository;
    final HashtagRepository hashtagRepository;
    final UserRepository userRepository;
    final BookMapRepository bookMapRepository;

    //해시태그 변경은 다 삭제하고 다시 넣는 구조
    @Override
    @Transactional
    public void tagssave(Long id, TagRequestDto tagRequestDto){
        BookMapEntity bookMap = bookMapRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 북맵이 없습니다."));
        List<String> tags = tagRequestDto.getTags();
        for(String tag: tags){
            if(hashtagRepository.existsByTag(tag)){
                HashTag hashTag = hashtagRepository.findByTag(tag).orElseThrow(
                        () -> new IllegalArgumentException("해당 해시태그가 없습니다."));
                mapTagRepository.save(new MapHashTag(hashTag, bookMap));
            }else{
                HashTag hashTag = new HashTag(tag);
                mapTagRepository.save(new MapHashTag(hashTag, bookMap));
            }
        }
    }

    @Override
    @Transactional
    public void tagsUpdate(Long id, TagRequestDto tagRequestDto){
        tagsDelete(id);
        tagssave(id, tagRequestDto);
    }

    @Override
    @Transactional
    public void tagsDelete(Long id){
        BookMapEntity bookMap = bookMapRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 북맵이 없습니다."));
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByBookMap(bookMap);
        mapTagRepository.deleteAllByBookMap(bookMap);
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
    public List<BookMapResponseDto1> findBookMapByTag(String tag) {
        /*
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByHashTag_Tag(tag);
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
        List<List<MapHashTag>>  mapHashTagList = new ArrayList<>();
        List<List<HashTag>> hashTagList = new ArrayList<>();
        for (MapHashTag mapHashTag : mapHashTags) {
            mapHashTagList.add(mapTagRepository.findAllByBookMap(mapHashTag.getBookMap()));
        }

        for(List<MapHashTag> mapHashTagsList1: mapHashTagList){
            List<HashTag> hashTags = new ArrayList<>();
            for(MapHashTag mapHashTag: mapHashTagsList1){
                hashTags.add(mapHashTag.getHashTag());
;            }
            hashTagList.add(hashTags);
        }
        for(MapHashTag mapHashTag: mapHashTags){
            bookMapResponseDtos.add(new BookMapResponseDto(mapHashTag.getBookMap()));
        }
        for(BookMapResponseDto bookMapResponseDto: bookMapResponseDtos){
            bookMapResponseDto.setHashTag(hashTagList.get(bookMapResponseDtos.indexOf(bookMapResponseDto)));
        }


         */
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByHashTag_Tag(tag);
        List<BookMapResponseDto1> bookMapResponseDtos = new ArrayList<>();
        for(MapHashTag mapHashTag : mapHashTags){
            List<MapHashTag> mapHashTagList = mapTagRepository.findAllByBookMap(mapHashTag.getBookMap());
            List<HashTag> hashTags = new ArrayList<>();

            for(MapHashTag mapHashTag1: mapHashTagList){
                hashTags.add(mapHashTag1.getHashTag());
            }
            List<String> strings = new ArrayList<>();
            for(HashTag hashTag: hashTags){
                strings.add(hashTag.getTag());
            }
            bookMapResponseDtos.add(new BookMapResponseDto1(mapHashTag.getBookMap(), strings));
        }

        //bookMapResponseDtos = null;
        return bookMapResponseDtos;
    }
}
