package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.weyx.entity.Author;
import uz.weyx.payload.AuthorDto;
import uz.weyx.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {


    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Author> authorList = authorService.getAll();
        return ResponseEntity.ok(authorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Author author = authorService.getById(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/par")
    public ResponseEntity<?> getByParam(@RequestParam(value = "name") String name) {
        List<Author> authorList = authorService.getByParam(name);
        return ResponseEntity.ok(authorList);
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<?> getByNewsId(@PathVariable Integer newsId) {
        Author author = authorService.getByNewsId(newsId);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AuthorDto authorDto) {
        boolean success = authorService.save(authorDto);
        return ResponseEntity.ok(success);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        boolean success = authorService.edit(id, authorDto);
        return ResponseEntity.status(success ? 200 : 409).body(success);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean success = authorService.delete(id);
        return ResponseEntity.status(success ? 200 : 409).body(success);
    }
}
