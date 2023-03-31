package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.CourseAddDto;
import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.dto.MediaDto;
import fr.aelion.streamer.dto.ModuleDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Media;
import fr.aelion.streamer.entities.Module;
import fr.aelion.streamer.repositories.CourseRepository;
import fr.aelion.streamer.repositories.MediaRepository;
import fr.aelion.streamer.repositories.ModuleRepository;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ModuleRepository moduleRepository;
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
        // Compute media time
        for (FullCourseDto fc : fullCourses) {
            for (ModuleDto m : fc.getModules()) {
                var medias = m.getMedias();
                m.setTotalTime(convertToTime(medias));
            }
        }
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

    @Override
    public void remove(int id) {
        // Préambule : récupérer le cours dans son intégralité
        var oCourse = repository.findById(id);

        if (oCourse.isPresent()) {
            // 1. Update all medias of all modules to null module
            for (Module module : oCourse.get().getModules()) {
                for (Media media : module.getMedias()) {
                    media.setModule(null);
                    mediaRepository.save(media);
                }
                moduleRepository.delete(module);
            }

            // 3. Remove course
            repository.delete(oCourse.get());
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public FullCourseDto add(CourseAddDto course) {
        var newCourse = new Course();
        newCourse.setTitle(course.getTitle());
        newCourse.setObjective(course.getObjective());

        newCourse = repository.save(newCourse);

        return modelMapper.map(newCourse, FullCourseDto.class);
    }

    private String convertToTime(Set<MediaDto> medias) {
        Float time = medias.stream()
                .map(m -> {
                    m.setTotalTime(LocalTime.MIN.plusSeconds(m.getDuration().longValue()).toString());
                    return m;
                })
                .map(m -> m.getDuration())
                .reduce(Float.valueOf(0), (subtotal, duration) -> subtotal + duration);

        var timeAsLong = Math.round(time);

        return LocalTime.MIN.plusSeconds(timeAsLong).toString();

    }
}
