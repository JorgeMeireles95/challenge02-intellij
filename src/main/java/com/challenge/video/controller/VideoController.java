package com.challenge.video.controller;

import com.challenge.video.dto.VideoRequest;
import com.challenge.video.dto.VideoResponse;
import com.challenge.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/video")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping
    public ResponseEntity<List<VideoResponse>> buscarVideos() {
        return ResponseEntity.ok().body(service.buscarVideos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponse> burcarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VideoResponse> cadastrarVideo(@Valid @RequestBody VideoRequest request) {
        return ResponseEntity.ok().body(service.cadastrarVideo(request));
    }

    @PutMapping
    public ResponseEntity<VideoResponse> atualizarVideo(@RequestBody @Valid VideoRequest request, Long id) {
        return ResponseEntity.ok().body(service.atualizarVideo(request, id));
    }

    @DeleteMapping
    public void deletarVideo(Long id) {
        service.deletarVideo(id);
    }

}
