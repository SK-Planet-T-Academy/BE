package com.asac7_hackathon.hackathon.domain.posts.entitiy;

import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import com.asac7_hackathon.hackathon.global.utils.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "posts")
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  private boolean state = Boolean.TRUE;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, length = 1000)
  private String content;

  private int likesCount = 0;
  private int commentsCount = 0;
  private int viewsCount = 0;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  private Category category;


  public Post() {
    this.likesCount = 0;
    this.commentsCount = 0;
    this.viewsCount = 0;
  }

  @PrePersist
  public void prePersist() {
    this.state = true;
  }

  public void modifyPost(String title, String content, Category category) {
    this.title = title != null ? title : this.title;
    this.content = content != null ? content : this.content;
    this.category = category != null ? category : this.category;
  }

  public void deletePost() {
    this.title = "삭제된 글입니다.";
    this.content = "삭제된 글입니다";
    this.state = false;
  }
}

