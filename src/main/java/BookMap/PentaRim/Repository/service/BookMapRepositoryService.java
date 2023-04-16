package BookMap.PentaRim.Repository.service;

import BookMap.PentaRim.BookMap.BookMap;

public interface BookMapRepositoryService {

    void join(BookMap bookMap);

    BookMap findBookMap(Long BookMapId);
}
