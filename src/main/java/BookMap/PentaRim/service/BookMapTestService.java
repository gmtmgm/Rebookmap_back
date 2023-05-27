package BookMap.PentaRim.service;

import BookMap.PentaRim.BookMap.Dto.BookMapResponseDto;
import BookMap.PentaRim.BookMap.Dto.TagRequestDto;

import java.util.List;

public interface BookMapTestService {

    void tagssave(Long id, TagRequestDto tagRequestDto);
    void tagsUpdate(Long id, TagRequestDto tagRequestDto);
    void tagsDelete(Long id);
    List<BookMapResponseDto> findBookMapByTag(String tag);

}
