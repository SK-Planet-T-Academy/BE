package com.asac7_hackathon.hackathon.domain.posts.controller;

import com.asac7_hackathon.hackathon.domain.posts.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/img")
@RequiredArgsConstructor
public class ImageController {
  private final ImageService imageService;
}
