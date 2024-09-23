package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.VideoDto;
import com.elearn.app.entities.Category;
import com.elearn.app.entities.Video;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.VideoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements  VideoService{
    @Autowired
    private VideoRepo videoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setVideoId(UUID.randomUUID().toString());
        Video video = modelMapper.map(videoDto, Video.class);
        Video savedVideo = videoRepository.save(video);
        return modelMapper.map(savedVideo, VideoDto.class);
    }

    @Override
    public VideoDto updateVideo(String videoId, VideoDto videoDto) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        modelMapper.map(videoDto, video);
        Video updatedVideo = videoRepository.save(video);
        return modelMapper.map(updatedVideo, VideoDto.class);
    }

    @Override
    public VideoDto getVideoById(String videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        return modelMapper.map(video, VideoDto.class);
    }

    @Override
    public Page<VideoDto> getAllVideos(Pageable pageable) {
        Page<Video> videos = videoRepository.findAll(pageable);
        List<VideoDto> dtos = videos.getContent()
                .stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, videos.getTotalElements());
    }

    @Override
    public void deleteVideo(String videoId) {
        videoRepository.deleteById(videoId);
    }

    @Override
    public List<VideoDto> searchVideos(String keyword) {
        List<Video> videos = videoRepository.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword, keyword);
        return videos.stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
    }
}
