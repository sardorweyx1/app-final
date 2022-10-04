package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Author;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;

import java.util.List;
import java.util.Optional;


@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

    Author findByAuthorId(Integer newId);

    @Query(value = "select tags from news join news_tags on news.news_id=news_tags.news_id and news.news_id=?1 join tags on tags.tag_id=news_tags.tag_id;", nativeQuery = true)
    List<Tag> getTagsByNewsId(Integer id); //news_id=?1 da muammo bo'ishi mumkin

    @Query(value = "select author from news join author on author.id=news.author_id and news.news_id=?1;", nativeQuery = true)
    Author getAuthorByNewsId(Integer id);

    @Query(value = "select comment from comment join news n on n.news_id = comment.news_news_id and news_id=?1;", nativeQuery = true)
    Comment getCommentByNewsId(Integer id);

    @Query(value = "select tags from news join news_tags on news.news_id=news_tags.news_id and news.news_id=?1 join tags on tags.tag_id=news_tags.tag_id;", nativeQuery = true)
    List<Tag> getTagsName(String name); //news_id=?1 da muammo bo'ishi mumkin


}
