package com.asac7_hackathon.hackathon.domain.posts.controller;

import com.asac7_hackathon.hackathon.domain.posts.dto.PostRequestDto.PostBoardReq;
import com.asac7_hackathon.hackathon.domain.posts.dto.PostResponseDto;
import com.asac7_hackathon.hackathon.domain.posts.repository.PostRepository;
import com.asac7_hackathon.hackathon.domain.posts.service.PostService;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import com.asac7_hackathon.hackathon.domain.users.repository.UserRepository;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;
  private final UserRepository userRepository;

  @PostMapping("")
  public ResponseEntity<PostResponseDto> create(@RequestBody PostBoardReq request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    PostResponseDto createPost = postService.createPost(request, user);
    return ResponseEntity.ok(createPost);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostResponseDto> readDetail(@PathVariable Long postId) {
    PostResponseDto post = postService.getPost(postId);
    return ResponseEntity.ok(post);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<List<PostResponseDto>> readToCategory(@PathVariable Category category) {
    List<PostResponseDto> posts = postService.getAllByCategory(category);
    return ResponseEntity.ok(posts);
  }

  @GetMapping("")
  public ResponseEntity<List<PostResponseDto>> readAll() {
    List<PostResponseDto> posts = postService.getPosts();
    return ResponseEntity.ok(posts);
  }

  //  modifyPost
  @PutMapping("/{postId}")
  public void update(
      @PathVariable Long postId,
      @RequestBody PostBoardReq request) {
    postService.modifyPost(postId, request.getTitle(), request.getContent(), request.getCategory());
  }

  @DeleteMapping ("/{postId}")
  public void delete(
      @PathVariable Long postId) {
    postService.deletePost(postId);
  }
}

