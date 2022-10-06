package uz.weyx.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> getAll() {
        List<Comment> commentList = commentService.getAll();
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getById(@PathVariable Integer commentId) {
        Comment comment = commentService.getById(commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{newsId}/comments")
    public ResponseEntity<?> getCommentByNewsId(@PathVariable Integer newsId){
        Comment comment=commentService.getCommentByNewsId(newsId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/sortasc")
    public ResponseEntity<?> sortAsc() {
        List<Comment> sortComment = commentService.sortAsc();
        return ResponseEntity.ok(sortComment);
    }

    @GetMapping("/sortdesc")
    public ResponseEntity<?> sortDesc() {
        List<Comment> sortComment = commentService.sortDesc();
        return ResponseEntity.ok(sortComment);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommentDto commentDto) {
        Comment comment = commentService.create(commentDto);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> edit(@PathVariable Integer commentId, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.edit(commentId, commentDto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Integer commentId) {
        boolean success = commentService.delete(commentId);
        return ResponseEntity.status(success ? 200 : 409).body(success);
    }


}
