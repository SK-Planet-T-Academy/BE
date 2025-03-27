package com.asac7_hackathon.hackathon.domain.posts.controller;

import com.asac7_hackathon.hackathon.domain.posts.dto.PostRequestDto;
import com.asac7_hackathon.hackathon.domain.posts.dto.PostRequestDto.PostBoardReq;
import com.asac7_hackathon.hackathon.domain.posts.dto.PostResponseDto;
import com.asac7_hackathon.hackathon.domain.posts.service.PostService;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @ResponseBody
  @PostMapping("")
  public ResponseEntity<PostResponseDto> create(@RequestBody PostBoardReq request) {
    PostResponseDto createPost = postService.createPost(request);
    return ResponseEntity.ok(createPost);
  }

  @ResponseBody
  @GetMapping("/{postId}")
  public ResponseEntity<PostResponseDto> readDetail(@PathVariable Long postId) {
    PostResponseDto post = postService.getPost(postId);
    return ResponseEntity.ok(post);
  }

  @ResponseBody
  @GetMapping("/{category}")
  public ResponseEntity<List<PostResponseDto>> readToCategory(@PathVariable Category category) {
    List<PostResponseDto> posts = postService.getAllByCategory(category);
    return ResponseEntity.ok(posts);
  }

  @ResponseBody
  @GetMapping("")
  public ResponseEntity<List<PostResponseDto>> readAll() {
    List<PostResponseDto> posts = postService.getPosts();
    return ResponseEntity.ok(posts);
  }

//  @ResponseBody
//  @GetMapping("/{userId}")
//  public ResponseEntity<List<PostResponseDto>> readToUser(Long userId){
//    List<PostResponseDto> posts = postService.getPostsByUser(userId);
//    return ResponseEntity.ok(posts);
//  }


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

