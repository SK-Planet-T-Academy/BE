package com.asac7_hackathon.hackathon.domain.posts.service;

import com.asac7_hackathon.hackathon.domain.posts.dto.PostRequestDto;
import com.asac7_hackathon.hackathon.domain.posts.dto.PostRequestDto.PostBoardReq;
import com.asac7_hackathon.hackathon.domain.posts.dto.PostResponseDto;
import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
//import com.asac7_hackathon.hackathon.domain.posts.repository.PostLikeRepository;
import com.asac7_hackathon.hackathon.domain.posts.repository.PostRepository;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import com.asac7_hackathon.hackathon.domain.users.repository.UserRepository;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;
  // u
  @Transactional
  public void modifyPost(Long postId, String title, String content, Category category) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("예외분리 필요"));
    post.modifyPost(title, content, category);
//    postRepository.save(post);
  }

  // c
  @Transactional
  public PostResponseDto createPost(PostBoardReq request, User user) {
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
  @Transactional
  public PostResponseDto getPost(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    if (!post.isState()) {
      throw new IllegalArgumentException("게시글 상태가 비활성화되어 있습니다.");
    }
    // 조회수 증가
    postRepository.updateViewCount(postId, 1);
    return PostResponseDto.of(post);
  }


//  public Post getPostById(Long postId) {
//    return postRepository.findByIdAndStateIsTrue(postId)
//                         .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
//}

  public List<PostResponseDto> getAllByCategory(Category category) {
    List<Post> posts = postRepository.findAllByCategory(category);
    return PostResponseDto.of(posts);
  }
  public List<PostResponseDto> getPosts() {
    List<Post> posts = postRepository.findAll();
    return PostResponseDto.of(posts);  // 전체 게시글 조회
  }


  // d
  @Transactional
  public void deletePost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("예외분리 필요"));
    post.deletePost(); // 삭제된 글임을 알리기 위해 상태를 변경
//    postRepository.save(post);
  }

  // 개수 변화
//  private void updateCount(Long postId, String field, int value) {
//    switch (field) {
//      case "likesCount":
//        postRepository.updateLikeCount(postId, value);
//        break;
//      case "commentsCount":
//        postRepository.updateCommentCount(postId, value);
//        break;
//      case "viewsCount":
//        postRepository.updateViewCount(postId, value);
//        break;
//      default:
//        throw new IllegalArgumentException("잘못된 필드 이름입니다.");
//    }
//  }

}
