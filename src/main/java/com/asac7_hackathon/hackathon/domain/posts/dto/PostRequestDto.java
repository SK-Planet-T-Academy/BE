package com.asac7_hackathon.hackathon.domain.posts.dto;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDto {

//  //  final 필드
//  final Long postId;
//  final boolean state;
//  final String title;
//  final String content;
//  final int likesCount = 0;
//  final int commentsCount = 0;
//  final int viewsCount = 0;
////  final long userId;
//  final Category category;
//  final Date create_at;
//  final Date update_at;

  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  public static class PostBoardReq {
    final String title;
    final String content;
    final Category category;
  }

}