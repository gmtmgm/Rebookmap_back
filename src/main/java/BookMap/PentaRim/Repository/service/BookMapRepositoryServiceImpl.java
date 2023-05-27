package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.*;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.*;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.service.BookMapTestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookMapRepositoryServiceImpl implements BookMapRepositoryService {
    final UserRepository userRepository;
    final BookRepository bookRepository;
    final BookMapRepository bookMapRepository;
    final BookMapDetailRepository bookMapDetailRepository;
    final BookListRepository bookListRepository;
    final BookMapTestService bookMapTestService;
    final HashtagRepository hashtagRepository;

    final MapTagRepository mapTagRepository;



    @Override
    public BookMap EntityToBookMap(Long bookMapId) { //저장소 > BookMap 객체
        BookMap bookMap = new BookMap();
        bookMap.setBookMapId(bookMapId);
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("북맵 없음" + bookMapId));
        List<BookMapDetailEntity> detailList = bookMapDetailRepository.findByBookMapEntity(bookMapEntity);
//        for(BookMapDetailEntity detail: detailList){
//            if (detail.getType() == "Book") {
//                List<BookListEntity> bookList = bookListRepository.findByBookMapDetailEntity(detail);
//                ArrayList<Book> map = new ArrayList<>();
//                for (BookListEntity b: bookList){
//                    Book book = b.getBook();
//                    map.add(book);
//                }
//                bookMap.addObj(map);
//            } else if (detail.getType() == "Memo") {
//                String memo = detail.getMemo();
//                bookMap.addObj(memo);
//            } else {
//                new IllegalArgumentException("타입 미지정" + detail.getBookMapDetailId());
//            }
//        }
        return bookMap;
    }

    @Override
    public List<BookMapResponseDto> findByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));
        List<BookMapEntity> bookMapEntity = bookMapRepository.findByUser(user); //예외처리 안하기!!
        List<List<MapHashTag>> mapHashTags = new ArrayList<>();
        for(BookMapEntity bookMap: bookMapEntity){
            mapHashTags.add(mapTagRepository.findAllByBookMap(bookMap));
        }

        /*
        List<List<HashTag>> hashTags = new ArrayList<>();
        for(List<MapHashTag> tags: mapHashTags){
                hashTags.add(hashtagRepository.findByMapHashTags(tags));
        }

         */
        List<BookMapResponseDto> bookMapList = new ArrayList<>();
        for (BookMapEntity bookMap : bookMapEntity){
            bookMapList.add(new BookMapResponseDto(bookMap, null));
        }
        return bookMapList;
    }

    @Override
    public List<BookMapDetailResponseDto> findByBookMapId(Long bookMapId){ //예외처리 다 빼기!!
        BookMapEntity bookMapEntity = bookMapRepository.findByBookMapId(bookMapId);
        List<BookMapDetailEntity> bookMapDetailEntiy = bookMapDetailRepository.findByBookMapEntityOrderByIndex(bookMapEntity);
        List<BookMapDetailResponseDto> bookMapDetailList = new ArrayList<>();
        for (BookMapDetailEntity bookMapDetail : bookMapDetailEntiy){
            bookMapDetailList.add(new BookMapDetailResponseDto(bookMapDetail));
        }
        return bookMapDetailList;
    }

    @Override
    public List<BookListResponseDto> findByBookMapDetailId(Long bookMapDetailId){ //책목록
        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findByBookMapDetailId(bookMapDetailId);
        List<BookListEntity> bookListEntity = bookListRepository.findByBookMapDetailOrderByIndex(bookMapDetailEntity);
        List<BookListResponseDto> bookList = new ArrayList<>();
        for (BookListEntity book : bookListEntity){
            bookList.add(new BookListResponseDto(book));
        }
        return bookList;
    }

    @Override
    public Book findByBookListId(Long bookListId){
        BookListEntity bookListEntity = bookListRepository.findByBookListId(bookListId);
        return bookListEntity.getBook();
    }

    @Override
    public BookMapDetailResponseDto BookMapDetailEntityToDto(Long bookMapDetailId){
        return new BookMapDetailResponseDto(bookMapDetailRepository.findByBookMapDetailId(bookMapDetailId));
    }

    @Override
    public void updateBookMap(Long bookMapId, BookMapRequestDto bookMapRequestDto){
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다. " + bookMapId));
        List<String> hashTags = new ArrayList<>();
        for(String tag: bookMapRequestDto.getHashTag()){
            hashTags.add(tag);
        }
        TagRequestDto tagRequestDto = new TagRequestDto(hashTags);
        bookMapTestService.tagsUpdate(bookMapId, tagRequestDto);
        bookMapEntity.update(
                bookMapRequestDto.getBookMapTitle(),
                bookMapRequestDto.getBookMapContent());
    }

    @Override
    public void updateBookMapDetail(Long bookMapDetailId, BookMapDetailRequestDto bookMapDetailRequestDto){
        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findById(bookMapDetailId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMapDetail이 없습니다. " + bookMapDetailId));
        bookMapDetailEntity.update(
                bookMapDetailRequestDto.getMemo(),
                bookMapDetailRequestDto.getIndex());
    }

    @Override
    public void updateBookList(Long bookListId, BookListRequestDto bookListRequestDto){
        BookListEntity bookListEntity = bookListRepository.findById(bookListId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookList가 없습니다. " + bookListId));
        bookListEntity.update(
                bookListRequestDto.getBook(),
                bookListRequestDto.getIndex());
    }

    @Override
    public void bookMapDelete(Long bookMapId){
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다." + bookMapId));
        bookMapRepository.delete(bookMapEntity);
    }

    @Override
    public void bookMapDetailDelete(Long bookMapDetailId){
        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findById(bookMapDetailId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMapDetail이 없습니다. " + bookMapDetailId));
        bookMapDetailRepository.delete(bookMapDetailEntity);
    }

    @Override
    public void bookListDelete(Long bookListId){
        BookListEntity bookListEntity = bookListRepository.findById(bookListId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookList가 없습니다." + bookListId));
        bookListRepository.delete(bookListEntity);
    }

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
    public List<BookMapResponseDto> findBookMapByTag(String tag) {
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
        List<BookMapResponseDto> bookMapResponseDtos = new ArrayList<>();
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
            bookMapResponseDtos.add(new BookMapResponseDto(mapHashTag.getBookMap(), strings));
        }

        //bookMapResponseDtos = null;
        return bookMapResponseDtos;
    }

}
