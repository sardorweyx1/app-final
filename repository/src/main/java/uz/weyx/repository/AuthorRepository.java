package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Author;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsByName(@Size(min = 3, max = 15) String name);

    @Query(value = "SELECT * FROM author a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    List<Author> getByPartName(String name);

    Optional<Author> findByName(@Size(min = 3, max = 15) String name);

    @Query(value = "select id, name from author join news n on author.id = n.author_id and n.news_id=:id", nativeQuery = true)
    Author getAuthorByNewsId(@Param("id") Integer id);
}
