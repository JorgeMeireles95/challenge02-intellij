package com.challenge.video.service;

import com.challenge.video.dto.VideoRequest;
import com.challenge.video.dto.VideoResponse;
import com.challenge.video.exception.BadRequestException;
import com.challenge.video.exception.NotFoundException;
import com.challenge.video.model.Video;
import com.challenge.video.repository.VideoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public List<VideoResponse> buscarVideos() {
        return VideoResponse.listaVideos(repository.findAll());
    }

    public VideoResponse buscarPorId(Long id) {
        return VideoResponse.of(repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum Video Encontrado!")));
    }

    public void deletarVideo(Long id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum Video Encontrado!")));
    }

    public VideoResponse cadastrarVideo(VideoRequest request) {
        if(!request.getUrl().matches("^https://.*")) {
            throw new BadRequestException("A URL deve inicar com https://");
        }
        return VideoResponse.of(repository.save(Video.of(request)));
    }

    @Transactional
    public VideoResponse atualizarVideo(VideoRequest request, Long id) {
        var video = repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum Video Encontrado!"));
        BeanUtils.copyProperties(Video.of(request), video, "id");
        try{
            repository.save(video);
        }catch (BadRequestException ex){
            throw new BadRequestException("Não foi possivel salvar o video");
        }
        return VideoResponse.of(video);
    }
}
