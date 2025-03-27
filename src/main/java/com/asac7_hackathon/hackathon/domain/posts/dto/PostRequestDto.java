package com.asac7_hackathon.hackathon.domain.posts.dto;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Post;
import com.asac7_hackathon.hackathon.domain.posts.types.Category;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDto {
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  public static class PostBoardReq {
    final String title;
    final String content;
    final Category category;
    final Integer userId;
  }
}