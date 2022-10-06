package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.weyx.entity.Author;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.entity.Tag;
import uz.weyx.payload.NewsDto;
import uz.weyx.payload.SearchDto;
import uz.weyx.service.NewsService;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {


    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<?> getAll(){
       List<News> newsList= newsService.getAll();
       return ResponseEntity.ok(newsList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
       News news= newsService.getById(id);
       return ResponseEntity.ok(news);
    }



    @GetMapping("/title")
    public ResponseEntity<?> byPartOfTitle(@RequestParam String partTitle){
        List<News> news = newsService.findByPartOfTitle(partTitle);
        return ResponseEntity.ok(news);
    }

    @GetMapping("/content")
    public ResponseEntity<?> byPartOfContent(@RequestParam String partContent){
        List<News> newsList= newsService.findByPartOfContent(partContent);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/author")
    public ResponseEntity<?> byPartOfAuthorName(@PageableDefault(page = 0,size = 5) @RequestParam String authorName){
        List<News> newsList=newsService.findByPartOfAuthorName(authorName);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/tag/name")
    public ResponseEntity<?> searchByListTag(@RequestParam Set<String> tagName){
       List<News> newsList= newsService.isTagList(tagName);
       return ResponseEntity.ok(newsList);
    }

    @GetMapping("/tag")
    public ResponseEntity<?> isTagIdSet(@RequestParam Set<Integer> tagsId){
       List<News> newsList= newsService.isTagIdSet(tagsId);
       return ResponseEntity.ok(newsList);
    }


    @GetMapping("/searchnews")
    public ResponseEntity<?> searchNews(@RequestBody SearchDto searchDto){
        List<News> newsList=newsService.searchNews(searchDto);
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/sortasc")
    public ResponseEntity<?> sortAsc(){
        List<News> newsList = newsService.sortASC();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/sortdesc")
    public ResponseEntity<?> sortDESC(){
        List<News> newsList=newsService.sortDESC();
        return ResponseEntity.ok(newsList);
    }

    @PostMapping("/creat")
    public ResponseEntity<?> saveNews(@RequestBody NewsDto newsDto){
        News news = newsService.saveNews(newsDto);
        return ResponseEntity.ok(news);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody NewsDto newsDto){
        News savedNews = newsService.edit(id, newsDto);
        return ResponseEntity.ok(savedNews);
    }


}
