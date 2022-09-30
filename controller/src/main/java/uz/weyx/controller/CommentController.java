package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.weyx.entity.Comment;
import uz.weyx.payload.CommentDto;
import uz.weyx.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping()
    public HttpEntity<?> getAll() {
        List<Comment> commentList = commentService.getAll();
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{commentId}")
    public HttpEntity<?> getById(@PathVariable Integer commentId) {
        Comment comment = commentService.getById(commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/sortasc")
    public HttpEntity<?> sortAsc() {
        List<Comment> sortComment = commentService.sortAsc();
        return ResponseEntity.ok(sortComment);
    }

    @GetMapping("/sortdesc")
    public HttpEntity<?> sortDesc() {
        List<Comment> sortComment = commentService.sortDesc();
        return ResponseEntity.ok(sortComment);
    }


    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody CommentDto commentDto) {
        Comment comment = commentService.create(commentDto);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public HttpEntity<?> edit(@PathVariable Integer commentId, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.edit(commentId, commentDto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public HttpEntity<?> delete(@PathVariable Integer commentId) {
        boolean success = commentService.delete(commentId);
        return ResponseEntity.status(success ? 200 : 409).body(success);
    }


}
