package com.asac7_hackathon.hackathon.domain.posts.dto;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {
  final Long postId;
  final boolean state;
  final String title;
  final String content;
  final int likesCount;
  final int commentsCount;
  final int viewsCount;
//  final User user;
  final Category category;
  final Date createAt;
  final Date updateAt;

  public static PostResponseDto of(Post entity) {
    return new PostResponseDto(
        entity.getPostId(),
        entity.isState(),
        entity.getTitle(),
        entity.getContent(),
        entity.getLikesCount(),
        entity.getCommentsCount(),
        entity.getViewsCount(),
//        entity.getUser(),
        entity.getCategory(),
        entity.getCreateAt(),
        entity.getUpdateAt()
    );
  }
  public static List<PostResponseDto> of(List<Post> entitys) {
    return entitys.stream()
            .filter(Post::isState)
            .map(PostResponseDto::of)
            .collect(Collectors.toList());
  }

}
