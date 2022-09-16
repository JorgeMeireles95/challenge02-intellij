package com.challenge.video.dto;

import com.challenge.video.model.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public static VideoResponse of(Video video){
        return VideoResponse.builder()
                .id(video.getId())
                .titulo(video.getTitulo() != null ? video.getTitulo() : "SEM_TITULO")
                .descricao(video.getDescricao())
                .url(video.getUrl())
                .build();
    }

    public static List<VideoResponse> listaVideos(List<Video> videos) {
        if ( videos == null ) {
            return null;
        }

        List<VideoResponse> list = new ArrayList<VideoResponse>( videos.size() );
        for ( Video video : videos ) {
            list.add( of( video ) );
        }

        return list;
    }
}
