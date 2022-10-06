package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Tag;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query(value = "SELECT * FROM tags t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%',?1,'%'))", nativeQuery = true)
    List<Tag> getByPartName(String name);

    boolean existsByName(@Size(min = 3, max = 5) String name);

    @Query(value = "select * from tags where tags.tag_id in :tagsId;", nativeQuery = true)
    List<Tag> getByTagListId(@Param("tagsId") Set<Integer> tagsId);

    @Query(value = "select * from tags where tags.name in (?1);", nativeQuery = true)
    Set<Tag> getByTagNames(@Param("tagNames") Set<String> tagNames);

    @Query(value = "select tags.tag_id,name from news join news_tags on news.news_id=news_tags.news_id and news.news_id=:id join tags on tags.tag_id=news_tags.tag_id", nativeQuery = true)
    List<Tag> getTagsByNewsId(@Param("id") Integer id); //news_id=?1 da muammo bo'ishi mumkin
}