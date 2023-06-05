package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.BookMap.*;
import BookMap.PentaRim.BookMap.Dto.*;
import BookMap.PentaRim.Repository.*;
import BookMap.PentaRim.User.model.User;
import BookMap.PentaRim.User.Repository.UserRepository;
import BookMap.PentaRim.service.BookMapService;
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
    final BookMapDetailRepository bookMapDetailRepository;
    final BookListRepository bookListRepository;

    final HashtagRepository hashtagRepository;
    final MapTagRepository mapTagRepository;
    final BookMapService bookMapService;
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
        for(BookMapDetailEntity detail: detailList){
            log.info("인덱스: "+detail.getIndex());
            if ("Book".equals(detail.getType())) {
                List<BookListEntity> bookList = bookListRepository.findByBookMapDetailOrderByIndex(detail);
                ArrayList<Book> map = new ArrayList<>();
//                LinkedHashMap<Long, Book> map = new LinkedHashMap<>();
                for (BookListEntity book: bookList){
//                    map.put(book.getBookListId(), book.getBook());
                    map.add(book.getBook());
                }
                bookMap.addObj(map);
            } else if ("Memo".equals(detail.getType())) {
                String memo = detail.getMemo();
                bookMap.addObj(memo);
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
    public ArrayList<BookMapDetailRequestDto> ToBookMapDetail(BookMap bookMap){
        ArrayList<BookMapDetailRequestDto> bookMapDetailRequestDtos = new ArrayList<>();
        ArrayList<BookMap.BookMapDetail> details = bookMap.getBookMapIndex();
        for (BookMap.BookMapDetail bookMapDetail : details){
            bookMapDetailRequestDtos.add(new BookMapDetailRequestDto(
                    bookMapDetail.getType(), bookMapDetail.getMemo(), details.indexOf(bookMapDetail)));
        }
        return bookMapDetailRequestDtos;
    }

    @Override
    public ArrayList<ArrayList<BookListRequestDto>> ToBookList(BookMap bookMap){
        ArrayList<ArrayList<BookListRequestDto>> bookListList = new ArrayList<>();
        ArrayList<BookMap.BookMapDetail> details = bookMap.getBookMapIndex();
        for (BookMap.BookMapDetail bookMapDetail : details){
            ArrayList<Book> books = bookMapDetail.getMap();
            ArrayList<BookListRequestDto> bookListRequestDtos = new ArrayList<>();
            for (Book book : books){
                bookListRequestDtos.add(new BookListRequestDto(book, books.indexOf(book)));
            }
            bookListList.add(bookListRequestDtos);
        }
        return bookListList;
    }


//    @Override
//    public LinkedHashMap<Long, BookMapDetailRequestDto> ToBookMapDetail(BookMap bookMap){
//        ArrayList<BookMap.BookMapDetail> details = bookMap.getBookMapIndex();
//        LinkedHashMap<Long, BookMapDetailRequestDto> map = new LinkedHashMap<>();
//        for (BookMap.BookMapDetail bookMapDetail : details){
//            map.put(bookMapDetail.getBookMapDetailId(), new BookMapDetailRequestDto(
//                    bookMapDetail.getType(), bookMapDetail.getMemo(), bookMapDetail.getIndex()));
//        }
//        return map;
//    }

//    @Override
//    public LinkedHashMap<Long, BookListRequestDto> ToBookList(BookMap bookMap){
//        ArrayList<BookMap.BookMapDetail> details = bookMap.getBookMapIndex();
//        LinkedHashMap<Long, BookListRequestDto> map = new LinkedHashMap<>();
//        for (BookMap.BookMapDetail bookMapDetail : details) {
//            if (bookMapDetail.getType() == "Book" || bookMapDetail.getMap() != null) {
//                LinkedHashMap<Long, Book> detailMap = bookMapDetail.getMap();
//                ArrayList<Long> idList= new ArrayList<>(detailMap.keySet());
//                for (Long id : idList){
//                    map.put(id, new BookListRequestDto(detailMap.get(id), idList.indexOf(id)));
//                }
//            }
//        }
//        return map;
//    }


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

//    @Override
//    public List<BookMapDetailResponseDto> findByBookMapId(Long bookMapId){ //예외처리 다 빼기!!
//        BookMapEntity bookMapEntity = bookMapRepository.findByBookMapId(bookMapId);
//        List<BookMapDetailEntity> bookMapDetailEntiy = bookMapDetailRepository.findByBookMapEntityOrderByIndex(bookMapEntity);
//        List<BookMapDetailResponseDto> bookMapDetailList = new ArrayList<>();
//        for (BookMapDetailEntity bookMapDetail : bookMapDetailEntiy){
//            bookMapDetailList.add(new BookMapDetailResponseDto(bookMapDetail));
//        }
//        return bookMapDetailList;
//    }

//    @Override
//    public List<BookListResponseDto> findByBookMapDetailId(Long bookMapDetailId){ //책목록
//        BookMapDetailEntity bookMapDetailEntity = bookMapDetailRepository.findByBookMapDetailId(bookMapDetailId);
//        List<BookListEntity> bookListEntity = bookListRepository.findByBookMapDetailOrderByIndex(bookMapDetailEntity);
//        List<BookListResponseDto> bookList = new ArrayList<>();
//        for (BookListEntity book : bookListEntity){
//            bookList.add(new BookListResponseDto(book));
//        }
//        return bookList;
//    }

//    @Override
//    public Book findByBookListId(Long bookListId){
//        BookListEntity bookListEntity = bookListRepository.findByBookListId(bookListId);
//        return bookListEntity.getBook();
//    }

//    @Override
//    public BookMapDetailResponseDto BookMapDetailEntityToDto(Long bookMapDetailId){
//        return new BookMapDetailResponseDto(bookMapDetailRepository.findByBookMapDetailId(bookMapDetailId));
//    }




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

//    @Override
//    @Transactional
//    public void updateBookMapDetail(BookMapEntity bookMapEntity, BookMapDetailRequestDto bookMapDetailRequestDto){
//        List<BookMapDetailEntity> bookMapDetailEntities  = bookMapDetailRepository.findByBookMapEntity(bookMapEntity);
//        for (BookMapDetailEntity bookMapDetailEntity : bookMapDetailEntities){
//            bookMapDetailDelete(bookMapDetailEntity);
//        }
//        bookMapDetailRequestDto.setBookMapEntity(bookMapEntity);
//        saveBookMapDetail(bookMapEntity, bookMapDetailRequestDto);
//    }

//    @Override
//    @Transactional
//    public void updateBookList(BookMapDetailEntity bookMapDetailEntity, BookListRequestDto bookListRequestDto){
//        bookListRequestDto.setBookMapDetail(bookMapDetailEntity);
//        saveBookList(bookMapDetailId, bookListRequestDto);
//    }

    @Override
    @Transactional
    public BookMap updateBookMapAll(Long bookMapId, BookMap bookMap){
        bookMapDetailDelete(bookMapId);

        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다. " + bookMapId));
        updateBookMap(bookMapEntity, ToBookMapRequestDto(bookMap));
//        BookMapRequestDto bookMapRequestDto = ToBookMapRequestDto(bookMap);
//        BookMap newBookMap = new BookMap();
//        newBookMap.setBookMapId(bookMapId);
//        newBookMap.setBookMapTitle(bookMapRequestDto.getBookMapTitle());
//        newBookMap.setBookMapContent(bookMapRequestDto.getBookMapContent());
//        if (bookMapRequestDto.getHashTag() != null){
//            bookMap.setHashTag(bookMapRequestDto.getHashTag());
//        }

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

//    @Override
//    @Transactional
//    public BookMap updateBookMapAll(Long bookMapId, BookMapRequestDto bookMapRequestDto,
//                                    /*List<Long> bookMapDetailIds, */ArrayList<BookMapDetailRequestDto> bookMapDetailRequestDtos,
//                                    /*List<Long> bookListIds, */ArrayList<ArrayList<BookListRequestDto>> bookListRequestDtos){
//        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
//                .orElseThrow(() -> new
//                        IllegalArgumentException("해당 bookMap이 없습니다. " + bookMapId));
//        BookMap bookMap = new BookMap();
//        bookMap.setBookMapId(bookMapId);
//        bookMap.setBookMapTitle(bookMapRequestDto.getBookMapTitle());
//        bookMap.setBookMapContent(bookMapRequestDto.getBookMapContent());
//        if (bookMapRequestDto.getHashTag() != null){
//            bookMap.setHashTag(bookMapRequestDto.getHashTag());
//        }
////        int index = 0;
//        for (BookMapDetailRequestDto bookMapDetailRequestDto : bookMapDetailRequestDtos) { //삭제 후 저장부터
////            bookMapDetailRequestDto.setIndex(index);
//            updateBookMapDetail(bookMapEntity, bookMapDetailRequestDto);
////            index++;
//        }
//        List<BookMapDetailEntity> bookMapDetailEntities = bookMapDetailRepository.findByBookMapEntityOrderByIndex(bookMapEntity);
//        for (BookMapDetailEntity bookMapDetailEntity : bookMapDetailEntities){
////            int i = 0;
//            if (bookMapDetailEntity.getType() == "Memo"){
//                bookMapService.addMemo(bookMap, bookMapDetailEntity.getMemo());
//            }
//
//            else if (bookMapDetailEntity.getType() == "Book"){
//                for (ArrayList<BookListRequestDto> bookListRequestDto : bookListRequestDtos){
//                    ArrayList<Book> bookList = new ArrayList<>();
//                    if (bookMapDetailEntity.getIndex() == 0 && bookListRequestDtos.indexOf(bookListRequestDto) == 0){
//                        bookMapRequestDto.setBookMapImage(bookListRequestDto.get(0).getBook().getImage());
//                    }
////                    bookListRequestDto.setIndex(i);
//                    for (BookListRequestDto bookListDto : bookListRequestDto){
//                        bookList.add(bookListDto.getBook());
//                        saveBookList(bookMapDetailEntity, bookListDto);
//                    }
////                    i++;
//                    bookMapService.addMap(bookMap, bookList);
//                }
//            }
////            index++;
//        }
//        updateBookMap(bookMapEntity, bookMapRequestDto);
//        return bookMap;
//    }




    @Override
    @Transactional
    public void bookMapDelete(Long bookMapId){ //상위 객체를 삭제하면 모두 삭제되도록
        BookMapEntity bookMapEntity = bookMapRepository.findById(bookMapId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 bookMap이 없습니다." + bookMapId));
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
//
//    @Override
//    public void bookListDelete(Long bookListId){
//        BookListEntity bookListEntity = bookListRepository.findById(bookListId)
//                .orElseThrow(() -> new
//                        IllegalArgumentException("해당 bookList가 없습니다." + bookListId));
//        bookListRepository.delete(bookListEntity);
//    }



    //해시태그 변경은 다 삭제하고 다시 넣는 구조
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
        //bookMapResponseDtos = null;
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
