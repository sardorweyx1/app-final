package uz.weyx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.weyx.entity.Author;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;
import uz.weyx.payload.NewsDto;
import uz.weyx.payload.SearchDto;
import uz.weyx.payload.TagDto;
import uz.weyx.repository.AuthorRepository;
import uz.weyx.repository.NewsRepository;
import uz.weyx.repository.TagRepository;


import java.util.*;
import java.util.stream.Collectors;


import static org.springframework.data.jpa.domain.Specification.where;
import static uz.weyx.specification.NewSpecification.*;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;

    public List<News> getAll() {
        return newsRepository.findAll();
    }

    public News getById(Integer id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        return newsOptional.orElseGet(News::new);
    }

    public News saveNews(NewsDto newsDto) {
        Author author = authorRepository.findByName(newsDto.getAuthorName())
                .orElseGet(() -> {
                    Author newAuthor = new Author(newsDto.getAuthorName());
                    return authorRepository.save(newAuthor);
                });
        Set<Tag> tags = addNewTags(newsDto);
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setAuthor(author);
        news.addTags(tags);
        return newsRepository.save(news);
    }

    public News edit(Integer newsId, NewsDto newsDto) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found!"));

        Author author = authorRepository.findByName(newsDto.getAuthorName())
                .orElseGet(() -> {
                    Author newAuthor = new Author(newsDto.getAuthorName());
                    return authorRepository.save(newAuthor);
                });

        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setAuthor(author);

        Set<String> tagFromDto = newsDto.getTagNames();
        Set<String> newsTags = news.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
        tagFromDto.removeAll(newsTags);
        if (!tagFromDto.isEmpty()) {
            Set<String> allTags = tagService.getAll().stream().map(Tag::getName).collect(Collectors.toSet());
            tagFromDto.removeAll(allTags);
            if (!tagFromDto.isEmpty()) {
                Set<Tag> savedTags = new HashSet<>();
                for (String s : tagFromDto) {
                    TagDto tagDto = new TagDto();
                    tagDto.setName(s);
                    Tag save = tagService.save(tagDto);
                    savedTags.add(save);
                    break;
                }
                news.addTags(savedTags);
            }
        }
        return newsRepository.save(news);
    }

    private Set<Tag> addNewTags(NewsDto newsDto) {
        Set<Tag> tagsInRepo = tagRepository.getByTagNames(newsDto.getTagNames());
        Set<String> tagNames = newsDto.getTagNames();
        Set<Tag> newTags = new HashSet<>();
        if (tagsInRepo.size() != tagNames.size()) {
            Set<String> tagSet = tagsInRepo.stream().map(Tag::getName).collect(Collectors.toSet());
            tagNames.removeAll(tagSet);
            newTags = tagNames.stream().map(Tag::new).collect(Collectors.toSet());
        }
        return newTags;
    }

    public List<Tag> getByNewsId(Integer newsId) {
        return newsRepository.getTagsByNewsId(newsId);
    }

    public Author getAuthorByNewsId(Integer newsId) {
        return newsRepository.getAuthorByNewsId(newsId);
    }

    public Comment getCommentByNewsId(Integer newsId) {
        return newsRepository.getCommentByNewsId(newsId);
    }

    public List<News> findByPartOfTitle(String title) {
        return newsRepository.findAll(isPartOfTitle(title));
    }

    public List<News> findByPartOfContent(String content) {
        return newsRepository.findAll(isPartOfContent(content));
    }

    public List<News> findByPartOfAuthorName(String authName) {
        return newsRepository.findAll(isAuthorName(authName));
    }

    public List<News> isTagList(Set<String> tagName) {
        return newsRepository.findAll(isTagName(tagName));
    }

    public List<News> isTagIdSet(Set<Integer> tagsId) {
        return newsRepository.findAll(isTagsId(tagsId));
    }

    public List<News> searchNews(SearchDto searchDto) {
        return newsRepository.findAll(
                where(isPartOfTitle(searchDto.getPartOfTitle()))
                        .and(isPartOfContent(searchDto.getPartOfContent()))
                        .and(isAuthorName(searchDto.getAuthorName()))
                        .and(isTagName(searchDto.getTagNames()))
                        .and(isTagsId(searchDto.getTagsId()))
        );
    }

    public List<News> sortASC() {
        return newsRepository.findAll(Sort.by(Sort.Direction.ASC, "created"));
    }

    public List<News> sortDESC() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "modified"));
    }

}





