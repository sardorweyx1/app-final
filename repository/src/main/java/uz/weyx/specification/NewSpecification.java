package uz.weyx.specification;


import org.springframework.data.jpa.domain.Specification;
import uz.weyx.entity.Author;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;

import javax.persistence.criteria.*;
import java.util.Set;


public class NewSpecification {

    public static Specification<News> isPartOfTitle(String partOfTitle) {
        if (partOfTitle != null) {
            return (root, query, builder) -> builder
                    .like(root.get("title"), "%" + partOfTitle + "%");
        }
        return null;
    }

    public static Specification<News> isPartOfContent(String partOfContent) {
        if (partOfContent != null) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder
                    .like(root.get("content"), "%" + partOfContent + "%")
            );
        }
        return null;
    }

    public static Specification<News> isAuthorName(String authorName) {
        if (authorName != null) {
            return (root, query, criteriaBuilder) -> {
                Join<Author, News> authorNews = root.join("author");
                return criteriaBuilder.like(authorNews.get("name"), "%" + authorName + "%");
            };
        }
        return null;
    }

    public static Specification<News> isTagName(Set<String> tagNames) {
        if (tagNames != null) {
            return (root, query, criteriaBuilder) -> {
                Join<News, Tag> tagJoin = root.join("tags");
                Predicate news = null;
                for (String tagName : tagNames) {
                    news = criteriaBuilder.like(tagJoin.get("name"), "%" + tagName + "%");
                    break;
                }
                return news;
            };
        }
        return null;
    }

    public static Specification<News> isTagsId(Set<Integer> tagsId) {
        if (tagsId != null) {
            return (root, query, criteriaBuilder) -> {
                Join<Tag, News> tagNewsJoin = root.join("tags");
                Predicate news = null;
                for (Integer integer : tagsId) {
                    news = criteriaBuilder.equal(tagNewsJoin.get("id"), integer);
                    break;
                }
                return news;
            };
        }
        return null;
    }

}
