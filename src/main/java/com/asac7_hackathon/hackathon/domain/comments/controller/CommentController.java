package com.asac7_hackathon.hackathon.domain.comments.controller;

import com.asac7_hackathon.hackathon.domain.comments.dto.CommentRequestDto;
import com.asac7_hackathon.hackathon.domain.comments.dto.CommentResponseDto;
import com.asac7_hackathon.hackathon.domain.comments.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService service;



    @GetMapping("/api/comments/{postId}")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Integer postId) {
        List<CommentResponseDto> viewedAll = service.findAll(postId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(viewedAll);
    }


    @PostMapping("/api/comments")
    public ResponseEntity<CommentResponseDto> save(@RequestBody CommentRequestDto request) {
        CommentResponseDto saved = service.save(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saved);
    }


    @PatchMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> patchContent(@PathVariable Integer commentId, @RequestBody CommentRequestDto request) {
        CommentResponseDto patched = service.patchContent(commentId, request);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(patched);
    }


    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> delete(@PathVariable Integer commentId) {
        service.delete(commentId);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(null);
    }

}
