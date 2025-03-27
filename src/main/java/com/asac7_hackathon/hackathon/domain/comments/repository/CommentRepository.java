package com.asac7_hackathon.hackathon.domain.comments.repository;

import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;


public interface CommentRepository extends JpaRepository<Comment, Integer> {


    Comment save(Comment entity);

    List<Comment> findAll();

    Comment getById(Integer id);

    void deleteById(Integer id);
}
