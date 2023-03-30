package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.dto.MediaDto;
import fr.aelion.streamer.dto.ModuleDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.repositories.CourseRepository;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;

    @Autowired
    ModelMapper modelMapper;
    public List<FullCourseDto> findAll() {
        var fullCourses = repository.findAll()
                .stream()
                .map(c -> {
                    var fullCourseDto = modelMapper.map(c, FullCourseDto.class);
                    return fullCourseDto;
                })
                .collect(Collectors.toList());
        return fullCourses;
    }

    @Override
    public FullCourseDto findOne(int id) {
       return repository.findById(id)
               .map((c) -> {
                    var fullCourseDto =  modelMapper.map(c, FullCourseDto.class);
                    return fullCourseDto;
               })
               .orElseThrow();

    }

    private String convertToTime(Set<MediaDto> medias) {
        Float time = medias.stream()
                .map(m -> m.getDuration())
                .reduce(Float.valueOf(0), (subtotal, duration) -> subtotal + duration);
        var timeAsLong = Math.round(time);

        var hours = time / 3600;
        var minutes = (time % 3600) / 60;
        var seconds = time % 60;

        return String.format("%02d:%0dd:%02d", hours, minutes, seconds);

    }
}
