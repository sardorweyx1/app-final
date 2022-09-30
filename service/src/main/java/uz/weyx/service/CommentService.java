package uz.weyx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.weyx.entity.Comment;
import uz.weyx.entity.News;
import uz.weyx.payload.CommentDto;
import uz.weyx.repository.CommentRepository;
import uz.weyx.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;

    public Comment create(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(comment.getContent());
        Optional<News> byId = newsRepository.findById(commentDto.getNewsId());
        News news = byId.get();
        comment.setNews(news);
        return commentRepository.save(comment);

    }

    public Comment edit(Integer commentId, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) return new Comment();
        Comment comment = optionalComment.get();
        comment.setContent(comment.getContent());
        return comment;
    }

    public boolean delete(Integer commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) return false;
        commentRepository.deleteById(commentId);
        return true;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment getById(Integer commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElseGet(Comment::new);
    }

    public List<Comment> sortAsc() {
        return commentRepository.findAll(Sort.by(Sort.Direction.ASC, "created"));
    }

    public List<Comment> sortDesc() {
        return commentRepository.findAll(Sort.by(Sort.Direction.DESC, "modified"));
    }
}
