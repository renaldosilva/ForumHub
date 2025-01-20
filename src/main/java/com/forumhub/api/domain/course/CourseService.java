package com.forumhub.api.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    public CourseDetailDataDTO register(CourseRegistrationDataDTO data) {
        Course course = new Course(data);
        this.repository.save(course);
        return new CourseDetailDataDTO(course);
    }

    public Page<CourseDetailDataDTO> listAll(Pageable pageable) {
        return this.repository.findAll(pageable).map(CourseDetailDataDTO::new);
    }
}
