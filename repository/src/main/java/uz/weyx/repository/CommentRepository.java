package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "select comment from comment join news n on n.news_id = comment.news_news_id and n.news_id=:id", nativeQuery = true)
    Comment getCommentByNewsId(@Param("id") Integer id);

}
