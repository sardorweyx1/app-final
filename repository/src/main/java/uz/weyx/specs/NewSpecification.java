package uz.weyx.specs;

import org.springframework.data.jpa.domain.Specification;
import uz.weyx.entity.Author;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;
import javax.persistence.criteria.*;
import java.util.Set;


public class NewSpecification   {

    public static Specification<News> isPartOfTitle(String partOfTitle) {
        return (root, query, builder) -> builder
                .like(root.get("title"), "%" + partOfTitle + "%");
    }
    public static Specification<News> isPartOfContent(String partOfContent){
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("content"),"%" + partOfContent + "%")
        );
    }

    public static Specification<News> isAuthorName(String authorName){
        return (root, query, criteriaBuilder) -> {
            Join<Author,News> authorNews= root.join("author");
            return criteriaBuilder.like(authorNews.get("name"),"%"+authorName+"%");
        };
    }

    public static Specification<News> isTagList(Set<String> tagName){
        return ((root, query, criteriaBuilder) -> {
            Join<Tag,News> newsJoin=root.join("tag");
            return newsJoin.get("name").in(tagName);
        });
    }
    public static Specification<News> isTagIdSet(Set<Integer> tagsId){
        return ((root, query, criteriaBuilder) -> {
            Join<Tag,News> tagNewsJoin= root.join("tag");
            return tagNewsJoin.get("id").in(tagsId);
        });
    }

}
