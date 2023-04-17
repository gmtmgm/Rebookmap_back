package BookMap.PentaRim.Repository;


import BookMap.PentaRim.BookMap.BookMap;


public interface BookMapRepository {



    void save(BookMap bookMap);

    BookMap findByBookMapId(Long bookMapId);
}
