package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;

import java.util.List;



@Repository
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {

    @Query(value = "select tags from news join news_tags on news.news_id=news_tags.news_id and news.news_id=?1 join tags on tags.tag_id=news_tags.tag_id;", nativeQuery = true)
    List<Tag> getTagsName(String name); //news_id=?1 da muammo bo'ishi mumkin

}
