package com.asac7_hackathon.hackathon.domain.posts.dto;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserResponseDto;
import com.asac7_hackathon.hackathon.domain.users.repository.UserRepository;
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
  final UserResponseDto user;
  final Category category;

  public static PostResponseDto of(Post entity) {
    UserResponseDto userInfo = UserResponseDto.from(entity.getUser());
    return new PostResponseDto(
        entity.getPostId(),
        entity.isState(),
        entity.getTitle(),
        entity.getContent(),
        entity.getLikesCount(),
        entity.getCommentsCount(),
        entity.getViewsCount(),
        userInfo,
        entity.getCategory()
    );
  }
  public static List<PostResponseDto> of(List<Post> entitys) {
    return entitys.stream()
        .filter(Post::isState)
        .map(PostResponseDto::of)
        .collect(Collectors.toList());
  }

}
