package uz.weyx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.weyx.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
