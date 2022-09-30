package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.weyx.entity.Tag;
import uz.weyx.payload.TagDto;
import uz.weyx.service.TagService;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fap/tag")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public HttpEntity<List<Tag>> getAll() {
        List<Tag> tagList = tagService.getAll();
        return ResponseEntity.status(tagList.size() != 0 ? 200 : 409).body(tagList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        return ResponseEntity.status(!Objects.equals(tag, new Tag()) ? 200 : 409).body(tag);
    }

    @GetMapping("/par")
    public HttpEntity<?> getByParam(@RequestParam String name) {
        List<Tag> tagList = tagService.getByParam(name);
        return ResponseEntity.ok(tagList);
    }

    @PostMapping("/creat")
    public HttpEntity<?> save(@RequestBody TagDto tagDto) {
        boolean success = tagService.save(tagDto);
        return ResponseEntity.status(success ? 200 : 409).body(success);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        boolean success=tagService.delete(id);
        return ResponseEntity.status(success?200:409).body(success);
    }
}
