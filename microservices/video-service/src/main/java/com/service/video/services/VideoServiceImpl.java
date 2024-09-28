package com.service.video.services;

import com.service.video.documents.Video;
import com.service.video.dto.VideoDto;
import com.service.video.repositories.VideoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements  VideoService{
    @Autowired
    private VideoRepo videoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  CourseService courseService;

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setId(UUID.randomUUID().toString());
        Video video = modelMapper.map(videoDto, Video.class);
        Video savedVideo = videoRepository.save(video);
        return modelMapper.map(savedVideo, VideoDto.class);
    }

    @Override
    public VideoDto updateVideo(String videoId, VideoDto videoDto) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

       video.setTitle(videoDto.getTitle());
       video.setDesc(videoDto.getDesc());
       video.setFilePath(videoDto.getFilePath());
       video.setContentType(videoDto.getContentType());
       video.setCourseId(videoDto.getCourseId());

        Video updatedVideo = videoRepository.save(video);
        return modelMapper.map(updatedVideo, VideoDto.class);
    }

    @Override
    public VideoDto getVideoById(String videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoDto videoDto = modelMapper.map(video, VideoDto.class);
        videoDto.setCourse(courseService.getCourseById(videoDto.getCourseId()));

        return videoDto;


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

    @Override
    public VideoDto saveVideoFile(MultipartFile file, String videoId) {
        return null;
    }

    @Override
    public List<VideoDto> getVideoOfCourse(String courseId) {
        return videoRepository.findByCourseId(courseId).stream().map(video-> modelMapper.map(video,VideoDto.class)).collect(Collectors.toList());
    }
}
