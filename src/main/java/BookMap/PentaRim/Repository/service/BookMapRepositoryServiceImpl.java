package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.*;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.*;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.service.BookSaved;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookMapRepositoryServiceImpl implements BookMapRepositoryService {
    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BookMapRepository bookMapRepository;
    final BookMapScrapRepository bookMapScrapRepository;
    final BookMapDetailRepository bookMapDetailRepository;
    final BookListRepository bookListRepository;
    final HashtagRepository hashtagRepository;
    final MapTagRepository mapTagRepository;
    final BookSaved bookSaved;


    @Override
    @Transactional
    public BookMap EntityToBookMap(Long bookMapId) { //저장소 > BookMap 객체
        BookMap bookMap = new BookMap();
        bookMap.setBookMapId(bookMapId);
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("북맵 없음" + bookMapId));
        BookMapResponseDto bookMapResponseDto = new BookMapResponseDto(bookMapEntity);
        bookMapResponseDto.setHashTag(findHashTagByBookMap(bookMapEntity));
        bookMapResponseDto.toBookMap(bookMap);
        List<BookMapDetailEntity> detailList = bookMapDetailRepository.findByBookMapEntityOrderByIndex(bookMapEntity);
//        List<BookMapDetailResponseDto> detailResponseDtos = new ArrayList<>();
        for(BookMapDetailEntity detail: detailList){

            if ("Book".equals(detail.getType())) {
                List<BookListEntity> bookList = bookListRepository.findByBookMapDetailOrderByIndex(detail);
                ArrayList<Book> map = new ArrayList<>();
                for (BookListEntity book: bookList){
                    map.add(book.getBook());
                }
                bookMap.addObj(map, detail.getBookMapDetailId());
            } else if ("Memo".equals(detail.getType())) {
                String memo = detail.getMemo();
                bookMap.addObj(memo, detail.getBookMapDetailId());
            } else {
                new IllegalArgumentException("타입 미지정" + detail.getBookMapDetailId());
            }
        }

        return bookMap;
    }


    @Override
    public BookMapRequestDto ToBookMapRequestDto(BookMap bookMap){
        return (new BookMapRequestDto(bookMap.getBookMapTitle(), bookMap.getBookMapContent(),
                bookMap.getBookMapImage(), bookMap.getHashTag(), bookMap.isShare()));
    }


    @Override
    public List<String> findHashTagByBookMap(BookMapEntity bookMapEntity){
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByBookMap(bookMapEntity);
        List<String> hashTags = new ArrayList<>();
        for(MapHashTag mapHashTag: mapHashTags){
            hashTags.add(mapHashTag.getHashTag().getTag());
        }
        return hashTags;
    }


    @Override
    @Transactional
    public List<BookMapResponseDto> findBookMapsByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));
        List<BookMapEntity> bookMapEntity = bookMapRepository.findByUserOrderByBookMapSaveTime(user); //예외처리 안하기!!

        List<BookMapResponseDto> bookMapList = new ArrayList<>();
        for (BookMapEntity bookMap : bookMapEntity){
            List<String> hashTags = findHashTagByBookMap(bookMap);
            bookMapList.add(new BookMapResponseDto(bookMap, hashTags));
        }
        return bookMapList;
    }

    @Override
    @Transactional
    public List<BookMapResponseDto> findBookMapScrapsByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));
        List<BookMapScrapEntity> bookMapScrapEntities = bookMapScrapRepository.findByUserOrderByBookMapSaveTime(user);
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
        for (BookMapScrapEntity bookMapScrap : bookMapScrapEntities){
            bookMapResponseDtos.add(new BookMapResponseDto(bookMapScrap.getBookMap()));
        }
        return bookMapResponseDtos;
    }


    @Override
    @Transactional
    public void saveBookMap(Long userId, BookMapSaveRequestDto bookMapSaveRequestDto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));
        bookMapSaveRequestDto.setUser(user);
        bookMapSaveRequestDto.setBookMapSaveTime(LocalDateTime.now());
        bookMapSaveRequestDto.setShare(true);
        bookMapRepository.save(bookMapSaveRequestDto.toEntity());
    }

    public void saveBookMapDetail(BookMapEntity bookMapEntity, BookMapDetailRequestDto bookMapDetailRequestDto){
        bookMapDetailRequestDto.setBookMapEntity(bookMapEntity);
        bookMapDetailRepository.save(bookMapDetailRequestDto.toEntity());
    }

    public void saveBookList(BookMapDetailEntity bookMapDetailEntity, BookListRequestDto bookListRequestDto){
        bookListRequestDto.setBookMapDetail(bookMapDetailEntity);
        bookListRepository.save(bookListRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void saveBookMapScrap(Long userId, BookMapScrapRequestDto bookMapScrapRequestDto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));
        bookMapScrapRequestDto.setUser(user);
        bookMapScrapRequestDto.setBookMapSaveTime(LocalDateTime.now());
        bookMapScrapRepository.save(bookMapScrapRequestDto.toEntity());
    }


    @Override
    @Transactional
    public void updateBookMap(BookMapEntity bookMapEntity, BookMapRequestDto bookMapRequestDto){

        List<String> hashTags = new ArrayList<>();

        if (bookMapRequestDto.getHashTag() != null){
            List<String> tags = bookMapRequestDto.getHashTag();
            for(String tag: tags){
                hashTags.add(tag);
            }
        }
        TagRequestDto tagRequestDto = new TagRequestDto(hashTags);
        tagsUpdate(bookMapEntity, tagRequestDto);
        bookMapEntity.update(
                bookMapRequestDto.getBookMapTitle(),
                bookMapRequestDto.getBookMapContent(),
                bookMapRequestDto.getBookMapImage(),
                bookMapRequestDto.isShare());
    }

    @Override
    @Transactional
    public BookMap updateBookMapAll(Long bookMapId, BookMap bookMap){
        bookMapDetailDelete(bookMapId);

        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다. " + bookMapId));
        updateBookMap(bookMapEntity, ToBookMapRequestDto(bookMap));

        ArrayList<BookMap.BookMapDetail> bookMapDetails = bookMap.getBookMapIndex();
        ArrayList<ArrayList<BookListRequestDto>> listOfBookList = new ArrayList<>();

        for (BookMap.BookMapDetail bookMapDetail : bookMapDetails) {
            saveBookMapDetail(bookMapEntity, new BookMapDetailRequestDto(
                    bookMapDetail.getType(), bookMapDetail.getMemo(), bookMapDetails.indexOf(bookMapDetail)));
            if ("Book".equals(bookMapDetail.getType())){
                ArrayList<Book> map = bookMapDetail.getMap();
                ArrayList<BookListRequestDto> bookListRequestDtos = new ArrayList<>();
                for (Book book : map) {
                    bookSaved.saveBookToRepo(book.getIsbn());
                    bookListRequestDtos.add(new BookListRequestDto(book, map.indexOf(book)));
                }
                listOfBookList.add(bookListRequestDtos);
            }
        }
        List<BookMapDetailEntity> bookMapDetailEntities = bookMapDetailRepository.findByBookMapEntityOrderByIndex(bookMapEntity);
        int i = 0;
        for (BookMapDetailEntity bookMapDetailEntity : bookMapDetailEntities){
            if ("Book".equals(bookMapDetailEntity.getType())){
                ArrayList<BookListRequestDto> bookListRequestDtos = listOfBookList.get(i);
                for (BookListRequestDto bookListRequestDto : bookListRequestDtos){
                    saveBookList(bookMapDetailEntity, bookListRequestDto);
                }
                i++;
            }
        }

        return bookMap;
    }


    @Override
    @Transactional
    public void bookMapDelete(Long bookMapId){ //상위 객체를 삭제하면 모두 삭제되도록
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다." + bookMapId));
        tagsDelete(bookMapEntity);
        for (BookMapDetailEntity bookMapDetailEntity : bookMapDetailRepository.findByBookMapEntity(bookMapEntity)){
            for (BookListEntity bookListEntity : bookListRepository.findByBookMapDetail(bookMapDetailEntity)){
                bookListRepository.delete(bookListEntity);
            }
            bookMapDetailRepository.delete(bookMapDetailEntity);
        }
        bookMapRepository.delete(bookMapEntity);
    }

    @Override
    @Transactional
    public void bookMapDetailDelete(Long bookMapId){
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다." + bookMapId));
        for (BookMapDetailEntity bookMapDetailEntity : bookMapDetailRepository.findByBookMapEntity(bookMapEntity)){
            for (BookListEntity bookListEntity : bookListRepository.findByBookMapDetail(bookMapDetailEntity)){
                bookListRepository.delete(bookListEntity);
            }
            bookMapDetailRepository.delete(bookMapDetailEntity);
        }
    }

    @Override
    @Transactional
    public void tagssave(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto){
        List<String> tags = tagRequestDto.getTags();
        for(String tag: tags){
            if(hashtagRepository.existsByTag(tag)){
                HashTag hashTag = hashtagRepository.findByTag(tag).orElseThrow(
                        () -> new IllegalArgumentException("해당 해시태그가 없습니다."));
                mapTagRepository.save(new MapHashTag(hashTag, bookMapEntity));
            }else{
                HashTag hashTag = new HashTag(tag);
                mapTagRepository.save(new MapHashTag(hashTag, bookMapEntity));
            }
        }
    }

    @Override
    @Transactional
    public void tagsUpdate(BookMapEntity bookMapEntity, TagRequestDto tagRequestDto){
        tagsDelete(bookMapEntity);
        tagssave(bookMapEntity, tagRequestDto);
    }

    @Override
    @Transactional
    public void tagsDelete(BookMapEntity bookMapEntity){
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByBookMap(bookMapEntity);
        mapTagRepository.deleteAllByBookMap(bookMapEntity);
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
    public List<BookMapResponseDto> findBookMapByTag(String tag) {
        List<MapHashTag> mapHashTags = mapTagRepository.findAllByHashTag_Tag(tag);
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
        for(MapHashTag mapHashTag : mapHashTags){
            BookMapEntity bookMapEntity = mapHashTag.getBookMap();
            if (bookMapEntity.isShare()) {
                List<MapHashTag> mapHashTagList = mapTagRepository.findAllByBookMap(bookMapEntity);
                List<HashTag> hashTags = new ArrayList<>();

                for (MapHashTag mapHashTag1 : mapHashTagList) {
                    hashTags.add(mapHashTag1.getHashTag());
                }
                List<String> strings = new ArrayList<>();
                for (HashTag hashTag : hashTags) {
                    strings.add(hashTag.getTag());
                }
                bookMapResponseDtos.add(new BookMapResponseDto(bookMapEntity, strings));
            }
        }
        return bookMapResponseDtos;
    }

    @Override
    @Transactional
    public List<BookMapResponseDto> searchBookMap(String text){
        List<BookMapResponseDto> result = findBookMapByTag(text);
        List<BookMapEntity> bookMapResponseDtosTitle = bookMapRepository.findAllByBookMapTitleContaining(text);
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
        for (BookMapEntity bookMapEntity : bookMapResponseDtosTitle){
            bookMapResponseDtos.add(new BookMapResponseDto(bookMapEntity));
        }
        result.addAll(bookMapResponseDtos);
        return result;
    }
}
