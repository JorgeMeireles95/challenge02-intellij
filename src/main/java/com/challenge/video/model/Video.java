package com.challenge.video.model;


import com.challenge.video.dto.VideoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "url")
    private String url;

    public static Video of(VideoRequest request) {

        var video = new Video();
        BeanUtils.copyProperties(request, video);
        return video;
    }

}
