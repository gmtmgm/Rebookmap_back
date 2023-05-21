package BookMap.PentaRim.Repository;


import BookMap.PentaRim.BookMap.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<HashTag, Long> {
    Optional<HashTag> findByTag(String tag);
    boolean existsByTag(String tag);
    void deleteByTag(String string);
    void deleteById(Long id);
}
