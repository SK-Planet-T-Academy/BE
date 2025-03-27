package com.asac7_hackathon.hackathon.domain.comments.dto;

import com.asac7_hackathon.hackathon.domain.comments.model.Comment;
import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CommentRequestDto {

    private final String content;
    private final LocalDateTime createAt;
    private final Long postId;


    public Comment toEntity(Post post) {
        return new Comment(null, content, LocalDateTime.now(), null, post);
    }
}
