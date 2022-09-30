package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Tag;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query(value = "SELECT * FROM tag t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%',?1,'%'))", nativeQuery = true)
    List<Tag> getByPartName(String name);

    boolean existsByName(@Size(min = 3, max = 5) String name);

    @Query(value = "select * from tag where tag.id in :tagsId;", nativeQuery = true)
    List<Tag> getByTagListId(Set<Integer> tagsId);


}