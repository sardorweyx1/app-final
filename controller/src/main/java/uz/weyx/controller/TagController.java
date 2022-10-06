package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.weyx.entity.Tag;
import uz.weyx.payload.TagDto;
import uz.weyx.service.TagService;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> getAll() {
        List<Tag> tagList = tagService.getAll();
        return ResponseEntity.status(tagList.size() != 0 ? 200 : 409).body(tagList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        return ResponseEntity.status(!Objects.equals(tag, new Tag()) ? 200 : 409).body(tag);
    }

    @GetMapping("/par")
    public ResponseEntity<?> getByParam(@RequestParam String name) {
        List<Tag> tagList = tagService.getByParam(name);
        return ResponseEntity.ok(tagList);
    }

    @GetMapping("/{newsId}/tags")
    public ResponseEntity<?> getByNewsId(@PathVariable Integer newsId){
        List<Tag> tagList = tagService.getTagsByNewsId(newsId);
        return ResponseEntity.ok(tagList);
    }

    @PostMapping("/creat")
    public ResponseEntity<?> save(@RequestBody TagDto tagDto) {
        Tag tag = tagService.save(tagDto);
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        boolean success=tagService.delete(id);
        return ResponseEntity.status(success?200:409).body(success);
    }
}
