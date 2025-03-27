package com.asac7_hackathon.hackathon.domain.comments.dto;

import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private LocalDateTime createAt;


    public Comment toEntity() {
        return new Comment(null, content, LocalDateTime.now(), null, null);
    }
}
