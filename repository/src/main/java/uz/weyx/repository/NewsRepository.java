package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Author;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

    Author findByAuthorId(Integer newId);

    @Query(value = "select tag from news join news_tag on news.id=news_tag.news_id and news_id=?1 join tag on tag.id=news_tag.tag_id;", nativeQuery = true)
    List<Tag> getTagsByNewsId(Integer id);

    @Query(value = "select author from news join author on author.id=news.author_id and news.id=?1;", nativeQuery = true)
    Author getAuthorByNewsId(Integer id);

    @Query(value = "select comment from comment join news n on n.id = comment.news_id and news_id=?1;", nativeQuery = true)
    Comment getCommentByNewsId(Integer id);

}
