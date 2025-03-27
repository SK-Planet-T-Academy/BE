package com.asac7_hackathon.hackathon.domain.comments.repository;

import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepo {

    private final JdbcTemplate jdbcTemplate;

    public CommentRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void patchContent(Integer id, Comment entity) {

        Comment updateEntity = new Comment(id, entity.getContent(), entity.getCreateAt(), LocalDateTime.now(),
            entity.getLike());

        String patchQuery = "UPDATE comments SET content = ?, update_at = ? WHERE id = ?";
        jdbcTemplate.update(patchQuery, updateEntity.getContent(), updateEntity.getUpdateAt(), updateEntity.getId());
    }
}
