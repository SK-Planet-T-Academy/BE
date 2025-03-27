package com.asac7_hackathon.hackathon.domain.posts.repository;

import com.asac7_hackathon.hackathon.domain.posts.entitiy.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
  List<Image> findByPost_PostId(Long postId);
}
