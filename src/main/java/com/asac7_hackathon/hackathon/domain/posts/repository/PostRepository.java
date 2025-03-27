package com.asac7_hackathon.hackathon.domain.posts.repository;

import com.asac7_hackathon.hackathon.domain.posts.dto.PostResponseDto;
import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface PostRepository extends JpaRepository<Post, Long> {

  // 조회
  @Query("SELECT p FROM Post p WHERE p.postId = :id AND p.state = true")
  Post findByIdAndStateIsTrue(@Param("id") Long id);
  Optional<Post> findByPostId(Long id); // 단일 글 조회
//  List<Post> findAllByUserId(Long userId);// 유저가 작성한 글 조회
  List<Post> findAllByCategory(Category category); // 카테고리별 조회
  List<Post> findAll(); // 전체 조회

  @Modifying
  @Transactional
  @Query("UPDATE Post p SET p.likesCount = p.likesCount + :value WHERE p.postId = :postId")
  void updateLikeCount(Long postId, int value);

  @Modifying
  @Transactional
  @Query("UPDATE Post p SET p.commentsCount = p.commentsCount + :value WHERE p.postId = :postId")
  void updateCommentCount(Long postId, int value);

  @Modifying
  @Transactional
  @Query("UPDATE Post p SET p.viewsCount = p.viewsCount + :value WHERE p.postId = :postId")
  void updateViewCount(Long postId, int value);
}