package com.asac7_hackathon.hackathon.domain.posts.entitiy;

import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "posts")
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {

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

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id", nullable = false)
//  private User user;

  private Category category;

  @Column(name = "create_at")
  private Date createAt;

  @Column(name = "update_at")
  private Date updateAt;

  public Post() {
    this.likesCount = 0;
    this.commentsCount = 0;
    this.viewsCount = 0;
  }
  @PrePersist
  public void prePersist() {
    Date now = new Date();
    this.createAt = now;
    this.updateAt = now;
    this.state = true;
  }

  // 엔티티가 업데이트될 때 자동으로 호출
  @PreUpdate
  public void preUpdate() {
    this.updateAt = new Date();
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

