package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.weyx.entity.Author;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;
import uz.weyx.payload.NewsDto;
import uz.weyx.service.NewsService;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {


    private final NewsService newsService;

    @GetMapping
    public HttpEntity<?> getAll(){
       List<News> newsList= newsService.getAll();
       return ResponseEntity.ok(newsList);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
       News news= newsService.getById(id);
       return ResponseEntity.ok(news);
    }

    @GetMapping("/{newsId}/tags")
    public HttpEntity<?> getByNewsId(@PathVariable Integer newsId){
        List<Tag> tagList = newsService.getByNewsId(newsId);
        return ResponseEntity.ok(tagList);
    }

    @GetMapping("/{newsId}/authors")
    public HttpEntity<?> getAuthorByNewsId(@PathVariable Integer newsId){
        Author author=newsService.getAuthorByNewsId(newsId);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/{newsId}/comments")
    public HttpEntity<?> getCommentByNewsId(@PathVariable Integer newsId){
        Comment comment=newsService.getCommentByNewsId(newsId);
        return ResponseEntity.ok(comment);
    }


    @GetMapping("/parama")
    public HttpEntity<?> byPartOfTitle(@RequestParam String title){
        List<News> news = newsService.findByPartOfTitle(title);
        return ResponseEntity.ok(news);
    }

    @GetMapping("/par")
    public HttpEntity<?> byPartOfContent(@RequestParam String content){
        List<News> newsList= newsService.findByPartOfContent(content);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/parauth")
    public HttpEntity<?> byPartOfAuthorName(@PageableDefault(page = 0,size = 10) @RequestParam String authName){
        List<News> newsList=newsService.findByPartOfAuthorName(authName);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/parlist")
    public HttpEntity<?> searchByListTag(@RequestParam Set<String> tagName){
       List<News> newsList= newsService.isTagList(tagName);
       return ResponseEntity.ok(newsList);
    }

    @GetMapping("/parset")
    public HttpEntity<?> isTagIdSet(@RequestParam Set<Integer> tagsId){
       List<News> newsList= newsService.isTagIdSet(tagsId);
       return ResponseEntity.ok(newsList);
    }


    @GetMapping("/searchnews")
    public HttpEntity<?> searchNews(@RequestBody NewsDto newsDto){
        List<News> newsList=newsService.searchNews(newsDto);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/sortasc")
    public HttpEntity<?> sortAsc(){
        List<News> newsList = newsService.sortASC();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/sortdesc")
    public HttpEntity<?> sortDESC(){
        List<News> newsList=newsService.sortDESC();
        return ResponseEntity.ok(newsList);
    }

    @PostMapping("/creat")
    public HttpEntity<?> saveNews(@RequestBody NewsDto newsDto){
        boolean success=newsService.saveNews(newsDto);
        return ResponseEntity.status(success?200:409).body(success);
    }

    @PatchMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody NewsDto newsDto){
        News savedNews = newsService.edit(id, newsDto);
        return ResponseEntity.ok(savedNews);
    }


}
