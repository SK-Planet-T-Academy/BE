package com.asac7_hackathon.hackathon.domain.posts.service;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Image;
import com.asac7_hackathon.hackathon.domain.posts.repository.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
  public final ImageRepository imageRepository;

  public List<Image> getImage(Long postId) {
    return imageRepository.findByPost_PostId(postId);
  }
}
