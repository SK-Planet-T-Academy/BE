package com.asac7_hackathon.hackathon.domain.posts.service;

import com.asac7_hackathon.hackathon.domain.posts.dto.PostRequestDto.PostBoardReq;
import com.asac7_hackathon.hackathon.domain.posts.dto.PostResponseDto;
import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.repository.PostRepository;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;
  // u
  @Transactional
  public void modifyPost(Long postId, String title, String content, Category category) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다. 게시글 ID: " + postId));
    post.modifyPost(title, content, category);
  }

  // c
  @Transactional
  public PostResponseDto createPost(PostBoardReq request, User user) {
    if (request.getCategory() == null || !Category.isValidCategory(request.getCategory())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유효하지 않은 카테고리입니다.");
    }
    Post toSave = Post.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .category(request.getCategory())
        .user(user)
        .build();
    Post saved = postRepository.save(toSave);

    return PostResponseDto.of(saved);
  }

  // r
  @Transactional(readOnly = true)
  public PostResponseDto getPost(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> {
          log.error("게시글을 찾을 수 없습니다. ID : {}", postId);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다. 게시글 ID: " + postId);
        });

    if (!post.isState()) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글 상태가 비활성화되어 있습니다.");
    }

    // 조회수 증가
//    post.increaseView();
    postRepository.updateViewCount(postId,1);
    return PostResponseDto.of(post);
  }


  @Transactional(readOnly = true)
  public List<PostResponseDto> getAllByCategory(Category category) {
    if (category == null || !Category.isValidCategory(category)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유효하지 않은 카테고리입니다.");
    }
    List<Post> posts = postRepository.findAllByCategory(category);
    if (posts.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 글이 없습니다.");
    }
    return posts.stream()
        .filter(Post::isState)
        .map(PostResponseDto::of)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<PostResponseDto> getPosts() {
    List<Post> posts = postRepository.findAll();
    if (posts.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 글이 없습니다.");
    }
    return posts.stream()
        .filter(Post::isState)
        .map(PostResponseDto::of)
        .toList();
  }


  // d
  @Transactional
  public void deletePost(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> {
          log.error("게시글을 찾을 수 없습니다. ID : {}", postId);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다. 게시글 ID: " + postId);
        });
    post.deletePost();
  }

}
