package uz.weyx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.weyx.entity.Author;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;
import uz.weyx.payload.AuthorDto;
import uz.weyx.payload.NewsDto;
import uz.weyx.repository.AuthorRepository;
import uz.weyx.repository.NewsRepository;
import uz.weyx.repository.TagRepository;
import uz.weyx.specs.NewSpecification;

import java.util.*;


import static org.springframework.data.jpa.domain.Specification.where;
import static uz.weyx.specs.NewSpecification.*;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;
    private final AuthorService authorService;

    public List<News> getAll() {
        return newsRepository.findAll();
    }

    public News getById(Integer id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        return newsOptional.orElseGet(News::new);
    }

    public boolean saveNews(NewsDto newsDto) {
        List<Author> authorList = authorRepository.getByPartName(newsDto.getAuthorName());
        if (authorList.isEmpty()) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setName(newsDto.getAuthorName());
            authorService.save(authorDto);
        }
        List<Tag> tagList = tagRepository.getByTagListId(newsDto.getTag());
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setAuthor(authorList.get(authorList.size()));
        news.setTag((Set<Tag>) tagList);
        newsRepository.save(news);
        return true;
    }

    public News edit(Integer newsId, NewsDto newsDto) {
        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isEmpty()) return null;
        News news = optionalNews.get();
        List<Author> authorList = authorRepository.getByPartName(newsDto.getAuthorName());
        if (authorList.isEmpty()) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setName(newsDto.getAuthorName());
            authorService.save(authorDto);
        }
        List<Tag> tagList = tagRepository.getByTagListId(newsDto.getTag());
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setAuthor(authorList.get(authorList.size()));
        news.setTag((Set<Tag>) tagList.get(tagList.size()));
        News save = newsRepository.save(news);
        return save;
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
        return newsRepository.findAll(NewSpecification.isTagList(tagName));
    }
    public List<News> isTagIdSet(Set<Integer> tagsId) {
        return newsRepository.findAll(NewSpecification.isTagIdSet(tagsId));
    }

    public List<News> searchNews(NewsDto newsDto) {
        return newsRepository.findAll(
                where(isPartOfTitle(newsDto.getTitle()))
                        .and(isPartOfContent(newsDto.getContent()))
                        .and(isAuthorName(newsDto.getAuthorName()))
                        .and(NewSpecification.isTagIdSet(newsDto.getTag()))
        );
    }
    public List<News> sortASC(){
        return newsRepository.findAll(Sort.by(Sort.Direction.ASC,"created"));
    }

    public List<News> sortDESC() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC,"modified"));
    }
}





