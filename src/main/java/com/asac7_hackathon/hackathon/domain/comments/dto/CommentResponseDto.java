package com.asac7_hackathon.hackathon.domain.comments.dto;

import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Integer commentId;

    private String content;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    private Post post;

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(comment.getCommentId(), comment.getContent(), comment.getCreateAt(),
            comment.getUpdateAt(), comment.getPost());
    }
}
