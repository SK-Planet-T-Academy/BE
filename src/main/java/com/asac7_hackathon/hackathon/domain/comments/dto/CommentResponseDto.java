package com.asac7_hackathon.hackathon.domain.comments.dto;

import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import com.asac7_hackathon.hackathon.domain.comments.model.Like;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Integer id;

    private String content;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    private List<Like> likeCount;

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreateAt(),
            comment.getUpdateAt(), comment.getLike());
    }
}
