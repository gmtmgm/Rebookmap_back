package BookMap.PentaRim.service;

import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto1;
import BookMap.PentaRim.BookMap.Dto.TagRequestDto;

import java.util.List;

public interface BookMapTestService {

    void tagssave(Long id, TagRequestDto tagRequestDto);
    void tagsUpdate(Long id, TagRequestDto tagRequestDto);
    void tagsDelete(Long id);
    List<BookMapResponseDto1> findBookMapByTag(String tag);

}
